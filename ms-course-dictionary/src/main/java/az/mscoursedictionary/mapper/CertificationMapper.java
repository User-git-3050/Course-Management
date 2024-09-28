package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.CertificationResponse;
import az.mscoursedictionary.entity.CertificationEntity;
import az.mscoursedictionary.entity.EnrollmentDetailsEntity;

public enum CertificationMapper {
    CERTIFICATION_MAPPER;

    public CertificationResponse mapToResponse(CertificationEntity certificationEntity){
        EnrollmentDetailsEntity enrollmentDetailsEntity = certificationEntity.getEnrollmentDetailsEntity();
        return CertificationResponse.builder()
                .id(certificationEntity.getId())
                .course(enrollmentDetailsEntity!=null?
                        enrollmentDetailsEntity.getCourse().getName():null)
                .pdfData(certificationEntity.getPdfData())
                .enrollment(enrollmentDetailsEntity!=null?
                        certificationEntity.getEnrollmentDetailsEntity().getEnrollment().getUsername():null)
                .dateIssued(certificationEntity.getDateIssued())
                .build();
    }
}
