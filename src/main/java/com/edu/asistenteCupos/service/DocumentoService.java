package com.edu.asistenteCupos.service;

import com.edu.asistenteCupos.Utils.dto.DocumentoAlumnoDto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DocumentoService {
  Logger logger = Logger.getLogger(AlumnoService.class.getName());

  private static DocumentoAlumnoDto getDocumentoAlumnoDto(String[] cabecera, String[] valores) {
    DocumentoAlumnoDto documentoAlumnoDto = new DocumentoAlumnoDto();

    for (int i = 0; i < cabecera.length; i++) {
      String nombreColumna = cabecera[i].trim();
      String valor = valores[i];

      try {
        Field campo = DocumentoAlumnoDto.class.getDeclaredField(nombreColumna);
        campo.setAccessible(true);

        if (campo.getType().equals(String.class)) {
          campo.set(documentoAlumnoDto, valor);
        }
      } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException("Error al asignar valor a campo: " + nombreColumna, e);
      }
    }
    return documentoAlumnoDto;
  }

  public List<DocumentoAlumnoDto> csvADto(MultipartFile file) {
    List<DocumentoAlumnoDto> documentoAlumnoDtos = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),
      StandardCharsets.UTF_8)); CSVReader csvReader = new CSVReader(br)) {

      String[] cabecera = csvReader.readNext();
      if (cabecera == null) {
        throw new RuntimeException("El CSV está vacío.");
      }

      String[] valores;
      while ((valores = csvReader.readNext()) != null) {
        DocumentoAlumnoDto documentoAlumnoDto = getDocumentoAlumnoDto(cabecera, valores);

        documentoAlumnoDtos.add(documentoAlumnoDto);
      }
    } catch (IOException | CsvValidationException e) {
      throw new RuntimeException("Error al procesar el archivo CSV", e);
    }

    return documentoAlumnoDtos;
  }
}
