package ma.projects.rentcarsproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String description;
    private double price;
    private int CarYear;
    private String type;
    private double dailyRentalRate;
    private boolean available;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Category category;

    @ManyToOne
    private CarInspectionReport carInspectionReport;

    @ElementCollection
    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();





}
