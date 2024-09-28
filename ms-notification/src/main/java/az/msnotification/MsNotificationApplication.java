package az.msnotification;

import az.msnotification.event.StudentEnrolledEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class MsNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(StudentEnrolledEvent studentEnrolledEvent){
        //we can send email messages here
        log.info("Received notification for Student - {} ", studentEnrolledEvent.getUsername());
    }

}
