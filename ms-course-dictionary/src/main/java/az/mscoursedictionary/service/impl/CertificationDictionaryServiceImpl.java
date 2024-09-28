package az.mscoursedictionary.service.impl;

import az.mscoursedictionary.dao.CertificationResponse;
import az.mscoursedictionary.entity.CertificationEntity;
import az.mscoursedictionary.entity.EnrollmentDetailsEntity;
import az.mscoursedictionary.exception.NotFoundException;
import az.mscoursedictionary.repository.CertificationRepository;
import az.mscoursedictionary.repository.EnrollmentDetailsRepository;
import az.mscoursedictionary.service.CertificationDictionaryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static az.mscoursedictionary.enums.ErrorMessages.*;
import static az.mscoursedictionary.enums.Info.CERTIFICATE_OF_ACHIEVEMENT;
import static az.mscoursedictionary.mapper.CertificationMapper.CERTIFICATION_MAPPER;
import static java.lang.String.format;

@Service
public class CertificationDictionaryServiceImpl implements CertificationDictionaryService {
    private final CertificationRepository certificationRepository;
    private final EnrollmentDetailsRepository enrollmentDetailsRepository;

    @Autowired
    public CertificationDictionaryServiceImpl(CertificationRepository certificationRepository, EnrollmentDetailsRepository enrollmentDetailsRepository) {
        this.certificationRepository = certificationRepository;
        this.enrollmentDetailsRepository = enrollmentDetailsRepository;

    }

    @Override
    public List<CertificationResponse> getAllCertifications() {
        return certificationRepository.findAll().stream().map(CERTIFICATION_MAPPER::mapToResponse).toList();
    }

    @Override
    public CertificationResponse getCertificationById(Long certificationId) {
        return certificationRepository.findById(certificationId).map(CERTIFICATION_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                CERTIFICATION_NOT_FOUND.getMessage(),
                                certificationId
                        )
                ));
    }

    @Override
    public CertificationResponse createCertificate(Long courseId, Long enrollmentId,byte[] pdfData){
        EnrollmentDetailsEntity enrollmentDetailsEntity = enrollmentDetailsRepository.findByCourseIdAndEnrollmentId(enrollmentId,courseId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND_WITH_COURSE.getMessage(),
                                courseId
                        )
                ));

        if(enrollmentDetailsEntity.getCertification() == null){
            CertificationEntity certificationEntity = CertificationEntity.builder()
                    .dateIssued(LocalDateTime.now())
                    .title(CERTIFICATE_OF_ACHIEVEMENT.getMessage())
                    .pdfData(pdfData)
                    .enrollmentDetailsEntity(enrollmentDetailsEntity)
                    .build();
            enrollmentDetailsEntity.setCertification(certificationEntity);
            enrollmentDetailsRepository.save(enrollmentDetailsEntity);
            return CERTIFICATION_MAPPER.mapToResponse(certificationEntity);
        }
        else{
            return null;
        }



    }


}
