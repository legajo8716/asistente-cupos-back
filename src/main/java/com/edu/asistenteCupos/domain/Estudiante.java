package com.edu.asistenteCupos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
  private String nombre;
  private String legajo;
  private String materia;
  private List<String> comisiones;
  private HistoriaAcademica historiaAcademica;
  private boolean correlativa;
}
