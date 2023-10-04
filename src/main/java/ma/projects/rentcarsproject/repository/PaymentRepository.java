package ma.projects.rentcarsproject.repository;

import ma.projects.rentcarsproject.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
