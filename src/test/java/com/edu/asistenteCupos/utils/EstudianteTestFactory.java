package com.edu.asistenteCupos.utils;

import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.domain.PeticionInscripcion;

import java.util.List;

public class EstudianteTestFactory {

  public static List<Estudiante> crearEstudiantesDePrueba() {
    return List.of(new Estudiante("Ana Torres", "1001", "Algoritmos y Estructuras de Datos",
        List.of("ALG101-COM1", "ALG101-COM2"), new HistoriaAcademica(2, 1, 1, 6, 4, 8), true),
      new Estudiante("Bruno Díaz", "1002", "Base de Datos", List.of("BD102-COM1"),
        new HistoriaAcademica(3, 2, 2, 8, 6, 6), true),
      new Estudiante("Carla Méndez", "1003", "Programación Avanzada",
        List.of("PA103-COM1", "PA103-COM2", "PA103-COM3"), new HistoriaAcademica(1, 1, 0, 4, 2, 10),
        false));
  }

  public static List<PeticionInscripcion> peticionInscripcionesDePrueba() {
    return List.of(new PeticionInscripcion(
      new Estudiante("Ana Torres", "1001", "Algoritmos y Estructuras de Datos",
        List.of("ALG101-COM1", "ALG101-COM2"), new HistoriaAcademica(2, 1, 1, 6, 4, 8), true),
      "Matematica", List.of(""), true), new PeticionInscripcion(
      new Estudiante("Carla Méndez", "1003", "Programación Avanzada",
        List.of("PA103-COM1", "PA103-COM2", "PA103-COM3"), new HistoriaAcademica(1, 1, 0, 4, 2, 10),
        false), "Matematica", List.of(""), true));
  }
}
