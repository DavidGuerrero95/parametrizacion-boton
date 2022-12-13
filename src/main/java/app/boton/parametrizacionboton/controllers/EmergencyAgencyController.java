package app.boton.parametrizacionboton.controllers;

import app.boton.parametrizacionboton.services.EmergencyAgencyService;
import app.boton.parametrizacionboton.services.IEmergencyAgencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/emergencyagency")
public class EmergencyAgencyController {

    private final IEmergencyAgencyService agencyService;


    public EmergencyAgencyController(EmergencyAgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(agencyService.listarEmergencyAgency());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearAgency(@RequestParam("name") String name,
                                         @RequestParam(value = "image") MultipartFile file,
                                         @RequestParam("phone") String phone) {
        if(!name.isEmpty() && !file.isEmpty() && !phone.isEmpty())
            return ResponseEntity.status(HttpStatus.CREATED).body(agencyService.crearImagen(name,file,phone));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los datos son necesarios");
    }

    @PutMapping("/editar/{name}")
    public ResponseEntity<?> editarAgency(@RequestParam("name") String name,
                                          @RequestParam("newName") String newName,
                                          @RequestParam(value = "image") MultipartFile file,
                                          @RequestParam("newPhone") String phone) {
        if(!name.isEmpty())
            return agencyService.editarImagen(name, newName, file, phone);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es requerido");

    }
}
