package ma.projects.rentcarsproject;

import ma.projects.rentcarsproject.entities.Car;
import ma.projects.rentcarsproject.entities.CarInspectionReport;
import ma.projects.rentcarsproject.entities.Category;
import ma.projects.rentcarsproject.entities.Location;
import ma.projects.rentcarsproject.repository.CarInspecReportRepository;
import ma.projects.rentcarsproject.repository.CategoryRepository;
import ma.projects.rentcarsproject.repository.LocationRepository;
import ma.projects.rentcarsproject.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication

public class RentCarsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentCarsProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CarService carService
            , LocationRepository locationRepository
            , CategoryRepository categoryRepository, CarInspecReportRepository carInspecReportRepository){
        return args -> {
            Car car =  Car.builder()
                    .make("Toyota")
                    .CarYear(2012)
                    .available(true)
                    .model("C12")
                    .price(400).build();
            Car car2 =  Car.builder()
                    .make("Dacia")
                    .CarYear(2012)
                    .available(true)
                    .model("Logan")
                    .price(300).build();
            Car car3 =  Car.builder()
                    .make("Renault")
                    .CarYear(2020)
                    .available(true)
                    .model("Capture")
                    .type("Normal")
                    .price(600).build();
            Car car4 =  Car.builder()
                    .make("Mercedes")
                    .CarYear(2020)
                    .available(true)
                    .model("Benz")
                    .price(1000).build();
            carService.createCar(car);
            carService.createCar(car2);
            carService.createCar(car3);
            carService.createCar(car4);

            List<String> imageUrl1 = new ArrayList<>();
            imageUrl1.add("DaciaLogan.jpg");
            List<String> imageUrl2 = new ArrayList<>();
            imageUrl2.add("Toyota Hybrid.jpg");
            List<String> imageUrl3 = new ArrayList<>();
            imageUrl3.add("R.jpg");
            carService.addImageUrlsToCar(car.getId(),imageUrl2);
            carService.addImageUrlsToCar(car2.getId(),imageUrl1);

        };
    }
}
