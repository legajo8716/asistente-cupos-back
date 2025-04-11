package com.edu.asistenteCupos.domain.prompt;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Materia;
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

    Comision comision = Comision.builder().id("INF101COM1").materia(materia)
                                .horario("Lunes 10:00 a 13:00").cupo(5).build();

    String prompt = PromptBuilder.nuevo().conComisiones(List.of(comision)).construir();

    assertTrue(prompt.contains("## INSTRUCCIÓN PARA MODELO DE LENGUAJE ##"));
    assertTrue(prompt.contains("OBJETIVO: Mostrar las comisiones disponibles"));
    assertTrue(prompt.contains("- INF101COM1 | INF101 | Lunes 10:00 a 13:00 | Cupo: 5"));
  }

  @Test
  void construyePromptConLasPeticionesDeInscripcionYLaMuestraEnElPrompt() {
    String input = "Quiero anotarme a Programación I y Física I";

    String prompt = PromptBuilder.nuevo().conPeticionesDeInscripcion(input).construir();

    assertTrue(prompt.contains("## CONSULTA DEL USUARIO ##"));
    assertTrue(prompt.contains("Este es el pedido del alumno:"));
    assertTrue(prompt.contains(input));
  }

  @Test
  void construyePromptCompletoYLoMuestraEnElPrompt() {
    Materia mate = Materia.builder().nombre("Matemática Discreta").codigo("MAT201").build();

    Comision comision = Comision.builder().id("MAT201COM2").materia(mate)
                                .horario("Viernes 08:00 a 11:00").cupo(10).build();

    String prompt = PromptBuilder.nuevo().conMaterias(List.of(mate))
                                 .conComisiones(List.of(comision))
                                 .conPeticionesDeInscripcion("Necesito cursar MAT201 este cuatri.")
                                 .construir();

    assertTrue(prompt.contains("Matemática Discreta (MAT201)"));
    assertTrue(prompt.contains("MAT201COM2 | MAT201 | Viernes 08:00 a 11:00 | Cupo: 10"));
    assertTrue(prompt.contains("Necesito cursar MAT201 este cuatri."));
  }
}