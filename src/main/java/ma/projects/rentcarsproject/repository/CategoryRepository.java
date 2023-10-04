package ma.projects.rentcarsproject.repository;

import ma.projects.rentcarsproject.entities.Category;
import ma.projects.rentcarsproject.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByName(String name);
    Category findCategoryByNameContains(String keyword);
}
