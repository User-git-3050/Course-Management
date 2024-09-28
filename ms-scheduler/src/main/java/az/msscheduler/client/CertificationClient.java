package az.msscheduler.client;

import az.msscheduler.dao.response.CertificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "MS-CERTIFICATION"
)
public interface CertificationClient {

    @PostMapping("api/v1/certifications/generate")
    ResponseEntity<List<CertificationResponse>> generateCertification();

}
