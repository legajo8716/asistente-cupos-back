package com.edu.asistenteCupos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Estudiante {
  @Id
  private String legajo;
  private String nombre;
  private String mail;
  @OneToOne
  private HistoriaAcademica historiaAcademica;
}
