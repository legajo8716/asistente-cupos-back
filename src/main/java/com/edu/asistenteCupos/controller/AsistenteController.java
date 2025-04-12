package com.edu.asistenteCupos.controller;

import com.edu.asistenteCupos.Utils.dto.PeticionInscripcionDTO;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.mapper.EstudianteMapper;
import com.edu.asistenteCupos.service.AsistenteDeInscripcion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asistente")
@RequiredArgsConstructor
class AsistenteController {
  private final AsistenteDeInscripcion asistenteDeInscripcion;
  private final EstudianteMapper estudianteMapper;

  @PostMapping("/sugerencia-inscripcion")
  public ResponseEntity<String> sugerirInscripcion(@RequestBody PeticionInscripcionDTO peticionesDeInscripcion) {
    try {
      List<Estudiante> estudiantes = estudianteMapper.toDomainList(
        peticionesDeInscripcion.estudiantes());
      String respuesta = asistenteDeInscripcion.sugerirInscripcion(estudiantes);
      return ResponseEntity.ok(respuesta);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("Error en la consulta: " + e.getMessage());
    }
  }

  @PostMapping("/ver-prompt")
  public ResponseEntity<String> verPrompt(@RequestBody PeticionInscripcionDTO peticionesDeInscripcion) {
    try {
      List<Estudiante> estudiantes = estudianteMapper.toDomainList(
        peticionesDeInscripcion.estudiantes());
      return ResponseEntity.ok(asistenteDeInscripcion.mostrarPrompt(estudiantes));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("Error en la consulta: " + e.getMessage());
    }
  }
}