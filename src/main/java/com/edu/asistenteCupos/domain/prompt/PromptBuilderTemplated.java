package com.edu.asistenteCupos.domain.prompt;

import com.edu.asistenteCupos.Utils.JsonConverter;
import com.edu.asistenteCupos.Utils.PromptTemplateProvider;
import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.domain.PeticionInscripcion;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PromptBuilderTemplated {
  private String materias;
  private String comisiones;
  private List<PeticionInscripcion> peticionesDeInscripcion;
  private PromptTemplateProvider promptTemplateProvider;
  private String modelo;
  private double temperatura;

  private PromptBuilderTemplated() {}

  public static PromptBuilderTemplated nuevo(PromptTemplateProvider promptTemplateProvider) {
    if (promptTemplateProvider == null) {
      throw new IllegalArgumentException("PromptTemplateProvider no puede ser nulo.");
    }
    PromptBuilderTemplated builder = new PromptBuilderTemplated();
    builder.promptTemplateProvider = promptTemplateProvider;
    return builder;
  }

  public PromptBuilderTemplated conMaterias(List<Materia> materias) {
    this.materias = materias.stream().map(m -> "- " + m.getNombre() + " (" + m.getCodigo() + ")")
                            .collect(Collectors.joining("\n"));
    return this;
  }

  public PromptBuilderTemplated conComisiones(List<Comision> comisiones) {
    this.comisiones = comisiones.stream()
                                .map(c -> "- " + c.getHorario() + " (" + c.getCodigo() + ")")
                                .collect(Collectors.joining("\n"));
    return this;
  }

  public PromptBuilderTemplated conTemperatura(double temperatura) {
    this.temperatura = temperatura;
    return this;
  }

  public PromptBuilderTemplated conModelo(String modelo) {
    this.modelo = modelo;
    return this;
  }

  public PromptBuilderTemplated conPeticionesDeInscripcion(List<PeticionInscripcion> peticionesDeInscripcion) {
    this.peticionesDeInscripcion = peticionesDeInscripcion;
    return this;
  }

  public Prompt construir() {
    if (modelo == null || modelo.trim().isEmpty()) {
      throw new IllegalStateException("El modelo no puede ser nulo o vacío.");
    }
    if (temperatura < 0.0 || temperatura > 1.0) {
      throw new IllegalArgumentException("La temperatura debe estar entre 0.0 y 1.0.");
    }
    if (materias == null && comisiones == null && peticionesDeInscripcion == null) {
      throw new IllegalArgumentException("Pone al menos uno, master.");
    }

    Map<String, Object> variables = new HashMap<>();
    variables.put("materias", materias);
    variables.put("comisiones", comisiones);
    variables.put("peticiones", JsonConverter.toJson(peticionesDeInscripcion));

    return new Prompt(List.of(systemMessageDesde(variables), userMessageDesde(variables)),
      ChatOptions.builder().model(this.modelo).temperature(this.temperatura).build());
  }

  private Message userMessageDesde(Map<String, Object> variables) {
    PromptTemplate userPrompt = new PromptTemplate(promptTemplateProvider.userResource());
    return new UserMessage(userPrompt.create(variables).getContents());
  }

  private Message systemMessageDesde(Map<String, Object> variables) {
    SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(
      promptTemplateProvider.systemResource());
    return new SystemMessage(systemPromptTemplate.create(variables).getContents());
  }
}
