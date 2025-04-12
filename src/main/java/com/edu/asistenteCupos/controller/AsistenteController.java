package com.edu.asistenteCupos.controller;

import com.edu.asistenteCupos.Utils.dto.AsignacionCupoDto;
import com.edu.asistenteCupos.Utils.dto.PeticionInscripcionDTO;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.mapper.EstudianteMapper;
import com.edu.asistenteCupos.service.AsistenteDeInscripcion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asistente")
@RequiredArgsConstructor
class AsistenteController {
  private final AsistenteDeInscripcion asistenteDeInscripcion;
  private final EstudianteMapper estudianteMapper;

  @PostMapping("/sugerencia-inscripcion")
  public ResponseEntity<List<AsignacionCupoDto>> sugerirInscripcion(@RequestParam(required = false) MultipartFile file) {
    try {
      List<AsignacionCupoDto> cupos =asistenteDeInscripcion.sugerirInscripcion(file);
      return ResponseEntity.ok(cupos);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(new ArrayList<>());
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