package com.edu.asistenteCupos.Utils.dto;

import java.util.List;

public record EstudianteDTO(String nombre, String legajo, String materia, List<String> comisiones,
                            HistoriaAcademicaDTO historiaAcademica, boolean correlativa) {}