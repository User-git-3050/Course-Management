package az.mscoursedictionary.repository;

import az.mscoursedictionary.dao.InstructorResponse;
import az.mscoursedictionary.entity.InstructorEntity;
import az.mscoursedictionary.entity.UserEntity;
import az.mscoursedictionary.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u.email FROM UserEntity u where u.role = 'ADMIN'")
    List<String> findAllByRoleIsAdmin();

    @Query("SELECT u.instructor FROM UserEntity u where u.role='INSTRUCTOR'")
    List<InstructorEntity> findAllByRoleIsInstructor();

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmailOrUsername(String email, String username);
}
