package com.edu.asistenteCupos.domain;

import lombok.Data;

import java.util.List;

@Data
public class Estudiante {
  private String nombre;
  private String legajo;
  private String materia;
  private List<String> comisiones;
  private HistoriaAcademica historiaAcademica;
  private boolean correlativa;

  public Estudiante(String nombre, String legajo, String materia, List<String> comisiones, HistoriaAcademica historiaAcademica, boolean correlativa) {
    this.nombre = nombre;
    this.legajo = legajo;
    this.materia = materia;
    this.comisiones = comisiones;
    this.historiaAcademica = historiaAcademica;
    this.correlativa = correlativa;
  }

  public Estudiante() {
  }
}
