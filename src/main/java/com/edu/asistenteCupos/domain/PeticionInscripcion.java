package com.edu.asistenteCupos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PeticionInscripcion {
  private Estudiante estudiante;
  private String materia;
  private List<String> comisiones;
  private boolean cumpleCorrelativa;
}