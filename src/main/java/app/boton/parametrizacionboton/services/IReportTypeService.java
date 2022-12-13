package app.boton.parametrizacionboton.services;

import app.boton.parametrizacionboton.models.ReportType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IReportTypeService {

    List<ReportType> listarReportType();

    ResponseEntity<?>  crearImagen(String name, MultipartFile image, String color);

    ResponseEntity<?> editarImagen(String name, String newName, MultipartFile image, String color);

}
