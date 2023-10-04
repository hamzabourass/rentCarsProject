package ma.projects.rentcarsproject.security.repository;

import ma.projects.rentcarsproject.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
