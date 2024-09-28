package az.msreport.controller;

import az.msreport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("courses")
    public ResponseEntity<String> getCourseReport(@RequestParam Long courseId, @RequestHeader String username){
        return reportService.getCourseReport(courseId,username);
    }

    @GetMapping("students")
    public ResponseEntity<String> getStudentReport(@RequestParam String studentName,@RequestHeader String username){
        return reportService.getStudentReport(studentName,username);
    }

    @GetMapping("instructors")
    public ResponseEntity<String> getInstructorReport(@RequestParam String instructorName){
        return reportService.getInstructorReport(instructorName);
    }


}
