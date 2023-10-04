package ma.projects.rentcarsproject.repository;

import ma.projects.rentcarsproject.entities.CarInspectionReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInspecReportRepository extends JpaRepository<CarInspectionReport,Long> {
}
