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
}
