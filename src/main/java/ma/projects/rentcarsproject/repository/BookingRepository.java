package ma.projects.rentcarsproject.repository;

import ma.projects.rentcarsproject.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
