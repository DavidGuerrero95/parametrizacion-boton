package app.boton.parametrizacionboton.repository;

import app.boton.parametrizacionboton.models.EmergencyAgency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface IEmergencyAgencyRepository extends MongoRepository<EmergencyAgency, String> {

    boolean existsByName(@Param("name") String name);

    EmergencyAgency findByName(@Param("name") String name);

}
