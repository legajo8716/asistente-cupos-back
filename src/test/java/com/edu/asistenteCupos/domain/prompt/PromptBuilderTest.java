package com.edu.asistenteCupos.domain.prompt;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.domain.PeticionInscripcion;
import com.edu.asistenteCupos.utils.EstudianteTestFactory;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PromptBuilderTest {

  @Test
  void construyePromptConMateriasYContieneInstruccionesParaElModeloDeLenguaje() {
    Materia algebra = Materia.builder().nombre("Álgebra").codigo("MAT101")
                             .correlativas(Collections.emptyList()).build();

    String prompt = PromptBuilder.nuevo().conMaterias(List.of(algebra)).construir();

    assertTrue(prompt.contains("## INSTRUCCIÓN PARA MODELO DE LENGUAJE ##"));
    assertTrue(prompt.contains("OBJETIVO: Mostrar las materias disponibles"));
    assertTrue(prompt.contains("- Álgebra (MAT101)"));
  }

  @Test
  void construyePromptConCorrelativasYLasMuestraEnElPrompt() {
    Materia am1 = Materia.builder().nombre("Análisis Matemático I").codigo("MAT102")
                         .correlativas(Collections.emptyList()).build();
    Materia fisica = Materia.builder().nombre("Física I").codigo("FIS101")
                            .correlativas(List.of(am1)).build();

    String prompt = PromptBuilder.nuevo().conMaterias(List.of(fisica)).construir();

    assertTrue(prompt.contains("- Física I (FIS101) | Correlativas: MAT102"));
  }

  @Test
  void construyePromptConComisionesYLaMuestraEnElPrompt() {
    Materia materia = Materia.builder().nombre("Programación I").codigo("INF101").build();
    Comision comision = Comision.builder().codigo("INF101COM1").materia(materia)
                                .horario("Lunes 10:00 a 13:00").cupo(5).build();

    String prompt = PromptBuilder.nuevo().conComisiones(List.of(comision)).construir();

    assertTrue(prompt.contains("## INSTRUCCIÓN PARA MODELO DE LENGUAJE ##"));
    assertTrue(prompt.contains("OBJETIVO: Mostrar las comisiones disponibles"));
    assertTrue(prompt.contains("- INF101COM1 | INF101 | Lunes 10:00 a 13:00 | Cupo: 5"));
  }

  @Test
  void construyePromptConLasPeticionesDeInscripcionYLaMuestraEnElPrompt() {
    List<PeticionInscripcion> peticiones = EstudianteTestFactory.peticionInscripcionesDePrueba();

    String prompt = PromptBuilder.nuevo().conPeticionesDeInscripcion(peticiones).construir();

    assertTrue(prompt.contains("## INSTRUCCIÓN PARA MODELO DE LENGUAJE ##"));
    assertTrue(prompt.contains(
      "OBJETIVO: Sugerir una asignación de inscripciones a materias y comisiones para cada estudiante."));
    assertTrue(prompt.contains("Nombre: Ana Torres"));
    assertTrue(prompt.contains("Legajo: 1001"));
    assertTrue(prompt.contains("Materia solicitada: Algoritmos y Estructuras de Datos"));
  }

  @Test
  void construyePromptCompletoYLoMuestraEnElPrompt() {
    Materia mate = Materia.builder().nombre("Matemática Discreta").codigo("MAT201").build();
    Comision comision = Comision.builder().codigo("MAT201COM2").materia(mate)
                                .horario("Viernes 08:00 a 11:00").cupo(10).build();

    String prompt = PromptBuilder.nuevo().conMaterias(List.of(mate))
                                 .conComisiones(List.of(comision)).construir();

    assertTrue(prompt.contains("Matemática Discreta (MAT201)"));
    assertTrue(prompt.contains("MAT201COM2 | MAT201 | Viernes 08:00 a 11:00 | Cupo: 10"));
  }
}