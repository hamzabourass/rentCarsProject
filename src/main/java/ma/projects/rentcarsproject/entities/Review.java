package ma.projects.rentcarsproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projects.rentcarsproject.security.entities.User;


@Entity(name = "Review")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;


    private int rating;
    private String comment;

}

