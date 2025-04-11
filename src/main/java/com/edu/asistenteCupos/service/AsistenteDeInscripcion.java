package com.edu.asistenteCupos.service;

import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.service.factory.PromptFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AsistenteDeInscripcion {
  private final OpenAiChatModel chatModel;
  private final PromptFactory promptFactory;

  public String sugerirInscripcion(List<Estudiante> peticionesDeInscripcion) {
    Prompt prompt = promptFactory.crearPrompt(peticionesDeInscripcion);
    return chatModel.call(prompt).getResult().getOutput().toString();
  }

  public String mostrarPrompt(List<Estudiante> peticionesDeInscripcion) {
    Prompt prompt = promptFactory.crearPrompt(peticionesDeInscripcion);
    System.out.println("el prompt es: \n" + prompt.toString());
    return prompt.toString();
  }
}
