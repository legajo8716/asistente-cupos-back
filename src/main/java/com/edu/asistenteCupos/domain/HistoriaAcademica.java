package com.edu.asistenteCupos.domain;

import lombok.Data;

@Data
public class HistoriaAcademica {
  private int insc3;
  private int inscAct;
  private int aprobUlt;
  private int inscTot;
  private int aprobTot;
  private int restantes;

  public HistoriaAcademica(int insc3, int inscAct, int aprobUlt, int inscTot, int aprobTot, int restantes) {
    this.insc3 = insc3;
    this.inscAct = inscAct;
    this.aprobUlt = aprobUlt;
    this.inscTot = inscTot;
    this.aprobTot = aprobTot;
    this.restantes = restantes;
  }
}