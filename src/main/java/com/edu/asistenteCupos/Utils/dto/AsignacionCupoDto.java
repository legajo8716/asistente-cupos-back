package com.edu.asistenteCupos.Utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AsignacionCupoDto {

    private String alumno;
    private String materia;
    private int prioridad;
    private boolean cupoAsignado;
    private String explicacion;

    @JsonProperty("cumple con las correlativas")
    private String cumpleConLasCorrelativas;

}