package ma.projects.rentcarsproject.repository;

import ma.projects.rentcarsproject.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findLocationByName(String name);
    Location findLocationByNameContains(String keyword);
    Location findLocationByAddress(String adress);
}
