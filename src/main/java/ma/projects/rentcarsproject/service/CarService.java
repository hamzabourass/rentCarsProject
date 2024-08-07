package ma.projects.rentcarsproject.service;

import ma.projects.rentcarsproject.entities.Car;
import ma.projects.rentcarsproject.entities.CarInspectionReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CarService {


    public List<Car> findByModel(String model);
    public List<Car> findByMake(String make);
    public List<Car> getAllCars();

    public Optional<Car> getCarById(Long id);

    public Car createCar(Car car);

    public Optional<Car> updateCar(Long id);

    public void deleteCar(Long id);

    public void addCarToLocation(Long carId, String name);

    public void removeCarFromLocation(Long carId, String name);

    public void addImageUrlsToCar(Long carId, List<String> imageUrls);
    public Page<Car> search(String keyword, Pageable pageable);
    public void addCarToCategory(Long id,String categoryName);
    public void removeCarFromCategory(Long id,String categoryName);
    public void addInspectionReport(Long id, CarInspectionReport carInspectionReport);
    public void updateInspectionReport(Long id,CarInspectionReport carInspectionReport);


}
