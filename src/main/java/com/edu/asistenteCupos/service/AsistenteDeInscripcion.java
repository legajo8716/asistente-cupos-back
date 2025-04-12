package com.edu.asistenteCupos.service;

import com.edu.asistenteCupos.Utils.dto.AsignacionCupoDto;
import com.edu.asistenteCupos.Utils.dto.EstudianteCSV;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.prompt.PromptPrinter;
import com.edu.asistenteCupos.mapper.EstudianteMapper;
import com.edu.asistenteCupos.service.factory.PromptFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AsistenteDeInscripcion {
  private final OpenAiChatModel chatModel;
  private final PromptFactory promptFactory;
  private final EstudianteMapper estudianteMapper;
  private final Logger logger = Logger.getLogger(AsistenteDeInscripcion.class.getName());
  public String sugerirInscripcion(List<Estudiante> peticionesDeInscripcion) {
    Prompt prompt = promptFactory.crearPrompt(peticionesDeInscripcion);
    return chatModel.call(prompt).getResult().getOutput().toString();
  }
  public List<AsignacionCupoDto> sugerirInscripcion(MultipartFile peticionesDeInscripcion) {
    List<EstudianteCSV> estudiantesCSV;
    try {
      estudiantesCSV = new CsvToBeanBuilder<EstudianteCSV>(new InputStreamReader(peticionesDeInscripcion.getInputStream(), StandardCharsets.UTF_8))
              .withType(EstudianteCSV.class)
              .withIgnoreLeadingWhiteSpace(true)
              .build()
              .parse();
    }
    catch (Exception e) {
      throw new RuntimeException("Error al leer el archivo CSV: " + e.getMessage());
    }
    List<Estudiante> estudiantes = estudianteMapper.toDomainListFromCSV(estudiantesCSV);
    String sugerencia = this.sugerirInscripcion(estudiantes);
    logger.info(sugerencia);
    List<AsignacionCupoDto> asignaciones = parseoDeSugerencia(sugerencia);

    return asignaciones;
  }

  private List<AsignacionCupoDto> parseoDeSugerencia(String sugerencia) {
    //TODO: modificar esta etapa de parseo para que sea mas optimo
    String context = extraerJsonArray(sugerencia);
    ObjectMapper objectMapper = new ObjectMapper();
    List<AsignacionCupoDto> asignaciones=null;
    try {
      // Parsear el JSON extraído
      asignaciones = objectMapper.readValue(context,
              objectMapper.getTypeFactory().constructCollectionType(List.class, AsignacionCupoDto.class));
    } catch (Exception e) {
      // Manejo de errores si el JSON no es válido
      throw new RuntimeException("Error al parsear la sugerencia: " + e.getMessage());
    }
    return asignaciones;
  }

  public String extraerJsonArray(String sugerencia) {
    int inicio = sugerencia.indexOf("textContent=[") + "textContent=[".length();
    int fin = sugerencia.lastIndexOf("], metadata=");

    if (inicio >= 0 && fin > inicio) {
      String jsonArray = sugerencia.substring(inicio, fin).trim();
      return "[" + jsonArray + "]"; // agregamos los corchetes para cerrarlo correctamente
    } else {
      throw new RuntimeException("No se pudo encontrar el array de textContent");
    }
  }
  public String mostrarPrompt(List<Estudiante> peticionesDeInscripcion) {
    Prompt prompt = promptFactory.crearPrompt(peticionesDeInscripcion);
    System.out.println("el prompt es: \n" + PromptPrinter.imprimir(prompt, true));
    return PromptPrinter.imprimir(prompt, false);
  }
}
