package com.edu.asistenteCupos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
  private String nombre;
  private String legajo;
  private HistoriaAcademica historiaAcademica;
}
