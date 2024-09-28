package az.msscheduler.tasks;

import az.msscheduler.client.CertificationClient;
import az.msscheduler.dao.response.CertificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GeneratingCertificate {
    private final CertificationClient certificationClient;

    @Autowired
    public GeneratingCertificate(CertificationClient certificationClient) {
        this.certificationClient = certificationClient;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void generateCertification(){
        List<CertificationResponse> generatedCertifications;
        generatedCertifications=certificationClient.generateCertification().getBody();
        if(generatedCertifications!=null && !generatedCertifications.isEmpty()){
            for(CertificationResponse generatedCertification : generatedCertifications ){
                if(generatedCertification!=null){
                    log.info("Certification generated with id : {}", generatedCertification.getId());
                }

            }
        }
        log.info("Certification generation completed");


    }

}
