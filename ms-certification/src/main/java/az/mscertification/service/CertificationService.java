package az.mscertification.service;

import az.mscertification.dao.CertificationResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificationService {
    ResponseEntity<List<CertificationResponse>> getAllCertifications();

    ResponseEntity<CertificationResponse> getCertificationById(Long certificationId);

    List<CertificationResponse> generateCertification();

    ResponseEntity<InputStreamResource> downloadCertificate(Long certificationId);
}
