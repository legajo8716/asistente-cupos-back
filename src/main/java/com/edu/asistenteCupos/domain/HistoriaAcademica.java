package com.edu.asistenteCupos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class HistoriaAcademica {
  @Id
  private String legajo;
  private int insc3;
  private int inscAct;
  private int aprobUlt;
  private int inscTot;
  private int aprobTot;
  private int restantes;
  private String correlativas;
  @OneToMany
  private List<Materia> anotadas= new java.util.ArrayList<>();
}
