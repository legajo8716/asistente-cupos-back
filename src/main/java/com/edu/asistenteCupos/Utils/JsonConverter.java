package com.edu.asistenteCupos.Utils;

import com.edu.asistenteCupos.domain.PeticionInscripcion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public class JsonConverter {
  private static final ObjectMapper mapper = new ObjectMapper().enable(
    SerializationFeature.INDENT_OUTPUT);

  public static String toJson(PeticionInscripcion peticion) {
    try {
      return mapper.writeValueAsString(peticion);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al convertir a JSON", e);
    }
  }

  public static String toJson(List<PeticionInscripcion> peticiones) {
    try {
      return mapper.writeValueAsString(peticiones);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al convertir lista a JSON", e);
    }
  }

  public static List<Map<String, Object>> readValue(String json) {
    try {
      return mapper.readValue(json, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al parsear el JSON: " + e.getMessage());
    }
  }
}