package com.edu.asistenteCupos.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoader {

  public static List<String[]> leerCSV(String nombreArchivo, String separador) throws IOException {
    try (BufferedReader reader = crearBufferedReader(nombreArchivo)) {
      return reader.lines().filter(line -> !line.isBlank()).map(line -> line.split(separador))
                   .map(arr -> {
                     for (int i = 0; i < arr.length; i++) {
                       arr[i] = arr[i].trim();
                     }
                     return arr;
                   }).toList();
    }
  }

  public static List<String> leerLineas(String nombreArchivo) throws IOException {
    try (BufferedReader reader = crearBufferedReader(nombreArchivo)) {
      return reader.lines().filter(line -> !line.isBlank()).collect(Collectors.toList());
    }
  }

  private static BufferedReader crearBufferedReader(String nombreArchivo) throws IOException {
    InputStream inputStream = Thread.currentThread().getContextClassLoader()
                                    .getResourceAsStream(nombreArchivo);

    if (inputStream == null) {
      throw new IllegalArgumentException("Archivo [%s] no encontrado".formatted(nombreArchivo));
    }

    return new BufferedReader(new InputStreamReader(inputStream));
  }
}