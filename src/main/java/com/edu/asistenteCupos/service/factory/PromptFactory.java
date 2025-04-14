package com.edu.asistenteCupos.service.factory;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.domain.PeticionInscripcion;
import com.edu.asistenteCupos.domain.prompt.PromptBuilder;
import com.edu.asistenteCupos.repository.ComisionRepository;
import com.edu.asistenteCupos.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromptFactory {
  private final MateriaRepository materiaRepository;
  private final ComisionRepository comisionRepository;
  private final String modelo = "gpt-4";
  private final double temperatura = 0.6;
  @Setter
  private String systemMessageFileName = "prompt/system-message.txt";
  @Setter
  private String criteriosFileName = "prompt/criterios-de-prioridad.txt";

  public Prompt crearPrompt(List<PeticionInscripcion> peticiones) {
    return new Prompt(List.of(new SystemMessage(construirSystemMessage()),
      new UserMessage(construirUserMessage(peticiones))),
      ChatOptions.builder().model(modelo).temperature(temperatura).build());
  }

  private String construirUserMessage(List<PeticionInscripcion> peticiones) {
    PromptBuilder builder = PromptBuilder.nuevo();

    List<Materia> materias = materiaRepository.findAll();
    builder.conMaterias(materias);

    List<Comision> comisiones = comisionRepository.findAll();
    builder.conComisiones(comisiones);

    builder.conPeticionesDeInscripcion(peticiones);
    return builder.construir();
  }

  private String construirSystemMessage() {
    String template = "";
    String criterios = "";
    template = String.join("\n", "FileLoader.leerLineas(systemMessageFileName)");
    criterios = String.join("\n", "FileLoader.leerLineas(criteriosFileName)");
    return template.replace("{{CRITERIOS}}", criterios);
  }
}
