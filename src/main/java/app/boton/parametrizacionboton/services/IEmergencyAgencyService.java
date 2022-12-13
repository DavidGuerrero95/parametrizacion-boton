package app.boton.parametrizacionboton.services;

import app.boton.parametrizacionboton.models.EmergencyAgency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmergencyAgencyService {

    List<EmergencyAgency> listarEmergencyAgency();

    ResponseEntity<?>  crearImagen(String name, MultipartFile image, String phone);

    ResponseEntity<?> editarImagen(String name, String newName, MultipartFile image, String phone);

}
