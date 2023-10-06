package ma.projects.rentcarsproject.repository;

import ma.projects.rentcarsproject.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    public List<Car> findCarByMake(String make);
    public List<Car> findCarByModel(String model);
    @Query("select c from Car c where c.make  like :x")
    public Page<Car> search(@Param("x") String keyword, Pageable pageable);


    @Modifying
    @Query("UPDATE Car c SET c.location = null WHERE c.id = :carId")
    void removeLocationFromCar(@Param("carId") Long carId);

    @Modifying
    @Query("UPDATE Car c SET c.category = null  WHERE c.id = :carId")
    void removeCategoryFromCar(@Param("carId") Long carId);

}
