package az.mscoursedictionary.repository;

import az.mscoursedictionary.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> {
    Optional<EnrollmentEntity> findByUsername(String name);
    List<EnrollmentEntity> findAllByUsernameIsIn(List<String> name);

}
