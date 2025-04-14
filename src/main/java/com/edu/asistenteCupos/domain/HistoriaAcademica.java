package com.edu.asistenteCupos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaAcademica {
  private int insc3;
  private int inscAct;
  private int aprobUlt;
  private int inscTot;
  private int aprobTot;
  private int restantes;
}