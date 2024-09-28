package az.mscoursedictionary.service;

import az.mscoursedictionary.dao.CertificationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificationDictionaryService {
    List<CertificationResponse> getAllCertifications();

    CertificationResponse getCertificationById(Long certificationId);

    CertificationResponse createCertificate(Long courseId, Long enrollmentId, byte[] pdfData);
}
