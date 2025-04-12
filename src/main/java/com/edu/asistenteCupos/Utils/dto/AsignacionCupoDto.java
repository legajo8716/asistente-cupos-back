package com.edu.asistenteCupos.Utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AsignacionCupoDto {

  @JsonProperty("alumno")
  private String alumno;

  @JsonProperty("materia")
  private String materia;

  @JsonProperty("prioridad")
  private int prioridad;

  @JsonProperty("cupoAsignado")
  private boolean cupoAsignado;

  @JsonProperty("explicacion")
  private String explicacion;

  @JsonProperty("cumpleCorrelativas")
  private boolean cumpleCorrelativas;

  @JsonProperty("modoCursada")
  private String modoCursada;

  // Getters y setters
  public String getAlumno() {
    return alumno;
  }

  public void setAlumno(String alumno) {
    this.alumno = alumno;
  }

  public String getMateria() {
    return materia;
  }

  public void setMateria(String materia) {
    this.materia = materia;
  }

  public int getPrioridad() {
    return prioridad;
  }

  public void setPrioridad(int prioridad) {
    this.prioridad = prioridad;
  }

  public boolean isCupoAsignado() {
    return cupoAsignado;
  }

  public void setCupoAsignado(boolean cupoAsignado) {
    this.cupoAsignado = cupoAsignado;
  }

  public String getExplicacion() {
    return explicacion;
  }

  public void setExplicacion(String explicacion) {
    this.explicacion = explicacion;
  }

  public boolean isCumpleCorrelativas() {
    return cumpleCorrelativas;
  }

  public void setCumpleCorrelativas(boolean cumpleCorrelativas) {
    this.cumpleCorrelativas = cumpleCorrelativas;
  }

  public String getModoCursada() {
    return modoCursada;
  }

  public void setModoCursada(String modoCursada) {
    this.modoCursada = modoCursada;
  }
}
