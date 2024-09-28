package az.mscoursedictionary.repository;

import az.mscoursedictionary.entity.EnrollmentDetailsEntity;
import az.mscoursedictionary.enums.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentDetailsRepository extends JpaRepository<EnrollmentDetailsEntity, Long> {
    Optional<EnrollmentDetailsEntity> findByCourseIdAndEnrollmentId(Long courseId, Long enrollmentId);
    List<EnrollmentDetailsEntity> findAllByStatusIs(EnrollmentStatus status);
    Optional<List<EnrollmentDetailsEntity>> findAllByCourseId(Long courseId);
    Optional<List<EnrollmentDetailsEntity>> findAllByProgressIsIn(List<Long> progress);
}
