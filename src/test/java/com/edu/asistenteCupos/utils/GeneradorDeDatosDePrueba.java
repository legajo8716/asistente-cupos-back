package com.edu.asistenteCupos.utils;

import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.domain.PeticionInscripcion;

import java.util.List;

public class GeneradorDeDatosDePrueba {
  public static List<PeticionInscripcion> peticionInscripcionesDePrueba() {
    Estudiante ana = new Estudiante("Ana Torres", "1001", new HistoriaAcademica(2, 1, 1, 6, 4, 8));
    Estudiante carla = new Estudiante("Carla Méndez", "1003",
      new HistoriaAcademica(1, 1, 0, 4, 2, 10));
    return List.of(new PeticionInscripcion(ana, "Matematica", List.of(""), true),
      new PeticionInscripcion(carla, "Matematica", List.of(""), true));
  }
}
