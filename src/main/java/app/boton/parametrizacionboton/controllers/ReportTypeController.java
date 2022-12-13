package app.boton.parametrizacionboton.controllers;

import app.boton.parametrizacionboton.services.IReportTypeService;
import app.boton.parametrizacionboton.services.ReportTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/reporttype")
public class ReportTypeController {

    private final IReportTypeService typeService;

    public ReportTypeController(ReportTypeService agencyService) {
        this.typeService = agencyService;
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(typeService.listarReportType());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearType(@RequestParam("name") String name,
                                       @RequestParam(value = "image") MultipartFile file,
                                       @RequestParam("color") String color) {
        if(!name.isEmpty() && !file.isEmpty() && !color.isEmpty())
            return ResponseEntity.status(HttpStatus.CREATED).body(typeService.crearImagen(name,file, color));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los datos son necesarios");
    }

    @PutMapping("/editar/{name}")
    public ResponseEntity<?> editarAgency(@RequestParam("name") String name,
                                          @RequestParam("newName") String newName,
                                          @RequestParam(value = "image") MultipartFile file,
                                          @RequestParam("color") String color) {
        if(!name.isEmpty())
            return typeService.editarImagen(name, newName, file, color);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es requerido");

    }
}
