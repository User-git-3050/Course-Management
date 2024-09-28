package az.msscheduler.tasks;

import az.msscheduler.client.CourseDictionaryClient;
import az.msscheduler.dao.response.InstructorResponse;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class GenerateReport {

    private final JavaMailSender mailSender;
    private final CourseDictionaryClient courseDictionaryClient;

    @Autowired
    public GenerateReport(JavaMailSender mailSender, CourseDictionaryClient courseDictionaryClient) {
        this.mailSender = mailSender;
        this.courseDictionaryClient = courseDictionaryClient;
    }

    private void sendGeneratedReport(String text, String recipientEmail) throws MessagingException, UnsupportedEncodingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Report for students and enrollments");
        message.setText(text);
        message.setFrom("mehreliyevelvin1@gmail.com");

        mailSender.send(message);
    }


    @Scheduled(cron = "0 * * * * * ")
    public void  sendReportsForAdmin() {
        List<String> adminEmails = courseDictionaryClient.getAdminEmails().getBody();

        String text ="Course report{\n"+
                Objects.requireNonNull(courseDictionaryClient.getAllCourseReport().getBody()) +
                "\n\nStudent report{\n"+
                Objects.requireNonNull(courseDictionaryClient.getAllStudentReport().getBody()) +
                "\n\nInstructor report{\n"+
                Objects.requireNonNull(courseDictionaryClient.getAllInstructorReport().getBody());

        adminEmails.forEach(email -> {
            try {
                sendGeneratedReport(text,email);
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Reports for admin was sent");
    }


    @Scheduled(cron = "0 * * * * * " )
    public void sendReportsForInstructors() {
        List<InstructorResponse> instructors = courseDictionaryClient.getInstructors().getBody();
        if (instructors != null) {
            instructors.forEach(instructor -> {

                List<String> courseReports = instructor.getCourses().stream()
                        .map(course->courseDictionaryClient.getCourseReport(course.getId(), instructor.getUsername()).getBody())
                        .toList();

                List<String> studentReports = instructor.getCourses().stream()
                        .flatMap(courseResponse -> courseResponse.getEnrollments().stream())
                        .map(enrollmentName->courseDictionaryClient.getStudentReport(enrollmentName, instructor.getUsername()).getBody())
                        .toList();

                try {
                    sendGeneratedReport("Course report{\n"+
                            courseReports +
                            "\nStudent report{\n"+
                            studentReports,instructor.getEmail()
                    );
                } catch (MessagingException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        log.info("Reports for instructors was sent");
    }




}
