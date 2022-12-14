package app.boton.parametrizacionboton.services;

import app.boton.parametrizacionboton.models.EmergencyAgency;
import app.boton.parametrizacionboton.repository.IEmergencyAgencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class EmergencyAgencyService implements IEmergencyAgencyService{

    private final IEmergencyAgencyRepository agencyRepository;

    public EmergencyAgencyService(IEmergencyAgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<EmergencyAgency> listarEmergencyAgency() {
        return agencyRepository.findAll();
    }

    @Override
    public ResponseEntity<?> crearImagen(String name, MultipartFile image, String phone) {

        if(!existeNombre(name)){
            EmergencyAgency agency;
            try {
                agency = new EmergencyAgency(name, new Date(), new Binary(image.getBytes()),
                        image.getContentType(), image.getSize(),
                        Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf(".")),
                        Base64.getEncoder().encodeToString(new Binary(image.getBytes()).getData()),
                        phone);
            } catch (IOException e) {
                log.error("ERROR Crear Imagen - Emergency Agency: " + e.getMessage() + " OTRO:" + e.getLocalizedMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creando imagen");
            }
            agencyRepository.save(agency);
        } else {
            editarImagen(name, name,image, phone);
        }
        log.info("guardo foto");
        return ResponseEntity.status(HttpStatus.CREATED).body("Creacion Correcta");
    }

    @Override
    public ResponseEntity<?> editarImagen(String name, String newName, MultipartFile image, String phone) {
        if(existeNombre(name)){
            EmergencyAgency agency = agencyRepository.findByName(name);
            if(!newName.isEmpty())
                agency.setName(name);
            if(!image.isEmpty()){
                try {
                    agency.setContent(new Binary(image.getBytes()));
                    agency.setContentType(image.getContentType());
                    agency.setSize(image.getSize());
                    agency.setSuffix(Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf(".")));
                    agency.setImage(Base64.getEncoder().encodeToString(new Binary(image.getBytes()).getData()));
                } catch (IOException e) {
                    log.error("ERROR Editar imagen - Emergency Agency: " + e.getMessage() + " OTRO:" + e.getLocalizedMessage());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error editando imagen");
                }
            }
            if(!phone.isEmpty())
                agency.setPhone(phone);
            agencyRepository.save(agency);
            return ResponseEntity.status(HttpStatus.OK).body("Evento modificado correctamente");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Evento no existe");
    }

    private Boolean existeNombre(String name){
        return agencyRepository.existsByName(name);
    }
}
