package com.edu.asistenteCupos.controller.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class PeticionInscripcionCsvDTO {
  @CsvBindByName
  private String nombre;

  @CsvBindByName
  private String legajo;

  @CsvBindByName
  private String materia;

  @CsvBindByName
  private String comisiones;

  @CsvBindByName
  private boolean correlativa;

  @CsvBindByName
  private int insc3;

  @CsvBindByName
  private int inscAct;

  @CsvBindByName
  private int aprobUlt;

  @CsvBindByName
  private int inscTot;

  @CsvBindByName
  private int aprobTot;

  @CsvBindByName
  private int restantes;
}
