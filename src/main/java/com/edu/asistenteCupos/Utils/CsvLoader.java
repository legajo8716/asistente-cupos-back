package com.edu.asistenteCupos.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvLoader {
  public static List<String[]> load(String nombreArchivo, String separador) throws Exception {
    InputStream inputStream = Thread.currentThread().getContextClassLoader()
                                    .getResourceAsStream(nombreArchivo);

    if (inputStream == null) {
      throw new IllegalArgumentException("CSV [%s] no encontrado".formatted(nombreArchivo));
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      return reader.lines().filter(line -> !line.isBlank()).map(line -> line.split(separador))
                   .map(arr -> {
                     for (int i = 0; i < arr.length; i++) {
                       arr[i] = arr[i].trim();
                     }
                     return arr;
                   }).toList();
    }
  }
}
