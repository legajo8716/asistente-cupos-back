package com.edu.asistenteCupos.domain;

import lombok.Data;

@Data
public class SugerenciaInscripcion {
  private Estudiante estudiante;

  private Comision comision;

  private boolean cupoAsignado;

  private String motivo;

  private int prioridad;
}
