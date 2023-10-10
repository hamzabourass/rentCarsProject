package ma.projects.rentcarsproject.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
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

    @NotEmpty
    @Size(min=4,max=20)
    private String make;
    @Size(min=4,max=20)
    private String model;
    @Size(max = 255)
    private String description;
    @NotNull
    @Min(1)
    private double price;
    @Min(1950)
    private int CarYear;
    @Size(max=20)
    private String type;
    @Max(5)
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
