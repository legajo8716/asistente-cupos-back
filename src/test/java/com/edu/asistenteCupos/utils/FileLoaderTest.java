package com.edu.asistenteCupos.utils;

import com.edu.asistenteCupos.Utils.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FileLoaderTest {

  @Test
  void leeCorrectamenteUnTexto() {
    List<String> lineas = null;
    try {
      lineas = FileLoader.leerLineas("lineas.txt");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertThat(lineas).hasSize(3);
    assertThat(lineas).containsExactly(
      "Primera línea",
      "Segunda línea",
      "Tercera línea"
    );
  }

  @Test
  void leeCorrectamenteUnCSV() {
    List<String[]> filas = null;
    try {
      filas = FileLoader.leerCSV("datos.csv", ",");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertThat(filas).hasSize(3);

    assertThat(filas.get(0)).containsExactly("nombre", "apellido", "edad");
    assertThat(filas.get(1)).containsExactly("Juan", "Perez", "30");
    assertThat(filas.get(2)).containsExactly("Ana", "Gomez", "25");
  }

  @Test
  void lanzaUnErrorSiElArchivoNoExiste() {
    assertThatThrownBy(() -> FileLoader.leerLineas("inexistente.txt"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("inexistente.txt");
  }
}
