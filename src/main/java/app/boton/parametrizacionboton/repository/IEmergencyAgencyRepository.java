package app.boton.parametrizacionboton.repository;

import app.boton.parametrizacionboton.models.ReportType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEmergencyAgencyRepository extends MongoRepository<ReportType, String> {
}
