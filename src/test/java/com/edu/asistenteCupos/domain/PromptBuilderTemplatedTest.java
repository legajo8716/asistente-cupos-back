package com.edu.asistenteCupos.domain;

import com.edu.asistenteCupos.Utils.PromptTemplateProvider;
import com.edu.asistenteCupos.domain.prompt.PromptBuilderTemplated;
import com.edu.asistenteCupos.utils.GeneradorDeDatosDePrueba;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PromptBuilderTemplatedTest {
  private PromptTemplateProvider promptTemplateProvider;
  private PromptBuilderTemplated promptBuilder;
  private List<Materia> materias;
  private List<Comision> comisiones;
  private List<PeticionInscripcion> peticiones;

  @BeforeEach
  void setUp() {
    promptTemplateProvider = Mockito.mock(PromptTemplateProvider.class);
    promptBuilder = PromptBuilderTemplated.nuevo(promptTemplateProvider);

    Materia matematica = new Materia("Matemáticas", "MAT101", List.of());
    Materia fisica = new Materia("Física", "FIS201", List.of());
    materias = Arrays.asList(matematica, fisica);

    comisiones = Arrays.asList(new Comision("Lunes 10:00", "COM001", 1, matematica),
      new Comision("Martes 14:00", "COM002", 3, fisica));

    peticiones = GeneradorDeDatosDePrueba.peticionInscripcionesDePrueba();
  }

  @Test
  void construirEntregaUnPromptCorrecto() {
    String systemPromptContent = "System prompt content";
    String userPromptContent = "User prompt content with {peticiones} with {materias} and {comisiones}";
    Resource systemResource = new ByteArrayResource(systemPromptContent.getBytes());
    Resource userResource = new ByteArrayResource(userPromptContent.getBytes());
    when(promptTemplateProvider.systemResource()).thenReturn(systemResource);
    when(promptTemplateProvider.userResource()).thenReturn(userResource);

    Prompt prompt = promptBuilder.conMaterias(materias).conComisiones(comisiones).conModelo("1")
                                 .conTemperatura(0.1).conPeticionesDeInscripcion(peticiones)
                                 .construir();

    String str = peticiones.toString();
    String resultado = str.substring(1, str.length() - 1);
    assertEquals(2, prompt.getInstructions().size());
    Message systemMessage = prompt.getInstructions().get(0);
    Message userMessage = prompt.getInstructions().get(1);
    String expectedSystemContent = "System prompt content";
    String expectedUserContent = "User prompt content with " + resultado +
      " with - MAT101 (Matemáticas)\n- FIS201 (Física) and - COM001 (Lunes 10:00)\n- COM002 (Martes 14:00)";

    assertEquals(expectedSystemContent, systemMessage.getText());
    assertEquals(expectedUserContent, userMessage.getText());
  }
}