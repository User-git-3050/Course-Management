package az.mscoursedictionary.repository;

import az.mscoursedictionary.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity,Long> {
    Optional<InstructorEntity> findByUsername(String username);
}
