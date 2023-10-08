package ma.projects.rentcarsproject.service;

import lombok.AllArgsConstructor;
import ma.projects.rentcarsproject.entities.Car;
import ma.projects.rentcarsproject.entities.CarInspectionReport;
import ma.projects.rentcarsproject.entities.Category;
import ma.projects.rentcarsproject.entities.Location;
import ma.projects.rentcarsproject.exceptions.CarNotFoundException;
import ma.projects.rentcarsproject.exceptions.CarServiceException;
import ma.projects.rentcarsproject.repository.CarInspecReportRepository;
import ma.projects.rentcarsproject.repository.CarRepository;
import ma.projects.rentcarsproject.repository.CategoryRepository;
import ma.projects.rentcarsproject.repository.LocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final CarInspecReportRepository carInspecReportRepository;


    @Override
    public List<Car> findByModel(String model) {
        try {
            List<Car> cars = carRepository.findCarByModel(model);

            if (cars.isEmpty()) {
                throw new CarNotFoundException("Car with model " + model + " not found");
            } else {
                return cars;
            }
        } catch (Exception e) {
            throw new CarServiceException("An error occurred while fetching car with ID " + model, e);
        }    }

    @Override
    public List<Car> findByMake(String name) {
        try {
            List<Car> cars = carRepository.findCarByMake(name);
            if (cars.isEmpty()){
                throw new CarNotFoundException("Car manufacturer " + name + " not found");
            }else {
                return cars;
            }
        }catch (Exception e) {
            throw new CarServiceException("An error occurred while fetching car with Make " + name, e);
        }
    }


    @Override
    public List<Car> getAllCars() {

        try {
            List<Car> cars = carRepository.findAll();
            if (cars.isEmpty()){
                throw new CarNotFoundException("Cars " + cars + " not exists");
            }else {
                return cars;
            }
        }catch (Exception e) {
            throw new CarServiceException("An error occurred while fetching cars" , e);
        }
    }

    @Override
    public Optional<Car> getCarById(Long id) {

        try {
            Optional<Car> car = carRepository.findById(id);

            if (car.isPresent()) {
                return car;
            } else {
                throw new CarNotFoundException("Car with ID " + id + " not found");
            }
        } catch (Exception e) {
            throw new CarServiceException("An error occurred while fetching car with ID " + id, e);
        }
    }
    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> updateCar(Long id) {
        Optional<Car> carFound = getCarById(id);
        Car car = carFound.orElse(null);
        carFound.ifPresent(c -> carRepository.save(car));
        return carFound;
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void addCarToLocation(Long carId, String name) {
        Optional<Car> carById = getCarById(carId);
        Location location = locationRepository.findLocationByName(name);
        carById.ifPresent(car -> car.setLocation(location));
    }

    @Override
    public void removeCarFromLocation(Long carId, String name) {
        Optional<Car> carById = getCarById(carId);
        carById.ifPresent(car -> carRepository.removeLocationFromCar(carId));
    }
    @Override
    public void addImageUrlsToCar(Long carId, List<String> imageUrls) {
        // Assuming you have a method to retrieve a Car by its ID
        Optional<Car> optionalCar = getCarById(carId);

        if (optionalCar.isPresent()) {

            Car car = optionalCar.get();
            List<String> existingImageUrls = car.getImageUrls();

            // Ajoutez chaque nouvelle URL d'image à la liste existante
            existingImageUrls.addAll(imageUrls);

            car.setImageUrls(existingImageUrls);
            carRepository.save(car);

        } else {
            // Handle the case where the Car with the given ID doesn't exist
            // You can throw an exception, log an error, or perform other error handling here
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        }
    }
    @Override
    public Page<Car> search(String keyword, Pageable pageable) {
        try {
            Page<Car> cars = carRepository.search("%"+keyword+"%",pageable);
            if (cars.isEmpty()){
                throw new CarNotFoundException("Car with  " + keyword + " not found");
            }else {
                return cars;
            }
        }catch (Exception e) {
            throw new CarServiceException("An error occurred while fetching car with keyword " + keyword, e);
        }        }

    @Override
    public void addCarToCategory(Long id, String categoryName) {
        Optional<Car> carById = getCarById(id);
        Category categoryByName = categoryRepository.findCategoryByName(categoryName);
        carById.ifPresent(car -> car.setCategory(categoryByName));
    }

    @Override
    public void removeCarFromCategory(Long id, String categoryName) {
        Optional<Car> carById = getCarById(id);
        carById.ifPresent(car -> carRepository.removeCategoryFromCar(id));
    }

    @Override
    public void addInspectionReport(Long id, CarInspectionReport carInspectionReport) {
        Optional<Car> carById = getCarById(id);
        carById.ifPresent(car ->car.setCarInspectionReport(carInspectionReport));

    }

    @Override
    public void updateInspectionReport(Long id ,CarInspectionReport carInspectionReport) {
        Optional<Car> carById = getCarById(id);
        carById.ifPresent(car ->{
            car.setCarInspectionReport(carInspectionReport);
            carInspecReportRepository.save(carInspectionReport);
            carRepository.save(car);
        });
    }

    @Override
    public void removeImageFromCar(Long carId, String imageName) {

        Optional<Car> carById = getCarById(carId);
        Car car = carById.orElse(null);
        if (car!=null){
            // Supprimez l'élément s'il correspond à la chaîne à supprimer
            car.getImageUrls().removeIf(element -> element.equals(imageName));
            carRepository.save(car);
        }
    }

        public void updateCar(Car updatedCar) {
            Optional<Car> existingCar = carRepository.findById(updatedCar.getId());

            if (existingCar.isPresent()) {
                Car car = existingCar.get();
                // Mettre à jour les autres propriétés de la voiture (make, model, etc.)
                car.setMake(updatedCar.getMake());
                car.setModel(updatedCar.getModel());
                car.setType(updatedCar.getType());
                car.setPrice(updatedCar.getPrice());
                car.setCarYear(updatedCar.getCarYear());
                car.setDailyRentalRate(updatedCar.getDailyRentalRate());
                car.setDescription(updatedCar.getDescription());
                car.setAvailable(updatedCar.isAvailable());
                // Mettre à jour les images uniquement si de nouvelles images ont été ajoutées
                List<String> existingImages = car.getImageUrls();
                List<String> newImages = updatedCar.getImageUrls();

                if (newImages != null && !newImages.isEmpty()) {
                    existingImages.addAll(newImages);
                    car.setImageUrls(existingImages);
                }

                carRepository.save(car);
            } else {
                // Gérer le cas où la voiture n'a pas été trouvée
                throw new CarNotFoundException("Car not found with ID: " + updatedCar.getId());
            }
        }
    }





