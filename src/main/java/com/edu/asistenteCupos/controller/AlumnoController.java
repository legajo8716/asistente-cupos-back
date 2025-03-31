package com.edu.asistenteCupos.controller;

import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
class AlumnoController {
    @Autowired
    AlumnoService alumnoService;


    @GetMapping

    public List<Alumno> getAlumnos() {

        return alumnoService.getAlumnos();
    }

    @GetMapping("/consultar")
    public ResponseEntity<String> consultar(@RequestParam String userInput) {
        try {
           // String respuesta = alumnoService.consultar(userInput);
            String respuesta = "La respuesta es: " + userInput ;
            ResponseEntity<String> response = ResponseEntity.ok(respuesta);
            System.out.println("Respuesta: " + response);

            return response; // Devuelve una respuesta 200 con el resultado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la consulta: " + e.getMessage());
        }
    }
}