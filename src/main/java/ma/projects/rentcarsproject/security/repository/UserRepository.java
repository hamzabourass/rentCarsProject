package ma.projects.rentcarsproject.security.repository;

import ma.projects.rentcarsproject.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
