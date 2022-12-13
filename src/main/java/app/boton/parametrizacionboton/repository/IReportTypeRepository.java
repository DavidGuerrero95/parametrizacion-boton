package app.boton.parametrizacionboton.repository;

import app.boton.parametrizacionboton.models.EmergencyAgency;
import app.boton.parametrizacionboton.models.ReportType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface IReportTypeRepository extends MongoRepository<ReportType, String> {

    boolean existsByName(@Param("name") String name);

    ReportType findByName(@Param("name") String name);
}
