package com.edu.asistenteCupos.domain;

import jakarta.persistence.*;
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
  @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
  private HistoriaAcademica historiaAcademica;
}
