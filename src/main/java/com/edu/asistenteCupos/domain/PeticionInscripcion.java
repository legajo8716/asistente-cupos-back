package com.edu.asistenteCupos.domain;

import lombok.Data;

import java.util.List;

@Data
public class PeticionInscripcion {
  private Estudiante estudiante;
  private String materia;
  private List<String> comisiones;
  private boolean cumpleCorrelativa;

  public PeticionInscripcion(Estudiante estudiante, String materia, List<String> comisiones, boolean cumpleCorrelativa) {
    this.estudiante = estudiante;
    this.materia = materia;
    this.comisiones = comisiones;
    this.cumpleCorrelativa = cumpleCorrelativa;
  }
}