package com.edu.asistenteCupos.controller;

import com.edu.asistenteCupos.Utils.dto.DocumentoAlumnoDto;
import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.service.AlumnoService;
import com.edu.asistenteCupos.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
class AlumnoController {
    @Autowired
    AlumnoService alumnoService;
    @Autowired
    DocumentoService documentService;

    @GetMapping

    public List<Alumno> getAlumnos() {

        return alumnoService.getAlumnos();
    }

    @PutMapping("/consultar")
    public ResponseEntity<String> consultar(@RequestBody String userInput,
                                            @RequestParam(value = "file", required = false) MultipartFile file ) {
        try {
            if(file != null){
                System.out.println("File: " + file.getOriginalFilename());
                List<DocumentoAlumnoDto> documentoAlumnoDtos = this.documentService.csvADto(file);
                return ResponseEntity.ok("Se procesaron " + documentoAlumnoDtos.size() + " registros");

            }


            String respuesta = alumnoService.consultar(userInput);

            ResponseEntity<String> response = ResponseEntity.ok(respuesta);
            System.out.println("Respuesta: " + response);

            return response; // Devuelve una respuesta 200 con el resultado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la consulta: " + e.getMessage());
        }
    }

    @PostMapping("/consultarcsv")
    public ResponseEntity<List<DocumentoAlumnoDto>> consultar(@RequestParam("files") MultipartFile[] files) {



        MultipartFile file = Arrays.stream(files).toList().get(0);
        System.out.println("File: " + file.getOriginalFilename());
        List<DocumentoAlumnoDto> documentoAlumnoDtos = this.documentService.csvADto(file);

       // List<AlumnoStatus> documentoAlumnoDtos = this.documentService.csvAAlumnoStatus(file);



        return ResponseEntity.ok(documentoAlumnoDtos);

    }
}