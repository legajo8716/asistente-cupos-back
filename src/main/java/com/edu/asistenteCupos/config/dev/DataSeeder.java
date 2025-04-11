package com.edu.asistenteCupos.config.dev;

import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class DataSeeder {

  private final MateriaRepository materiaRepository;
  String materiasCsv = "materias.csv";

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(materiasCsv);
      if (inputStream == null) {
        throw new FileNotFoundException("%s no encontrado".formatted(materiasCsv));
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      List<String[]> rows = reader.lines().filter(line -> !line.isBlank())
                                  .map(line -> line.split("\\|")).toList();

      Map<String, Materia> materias = crearMateriasDesde(rows);
      asociarCorrelativas(rows, materias);
    };
  }

  private Map<String, Materia> crearMateriasDesde(List<String[]> rows) {
    Map<String, Materia> materias = new HashMap<>();
    for (String[] row : rows) {
      String nombre = row[0].trim();
      String codigo = row[1].trim();

      Materia materia = Materia.builder().nombre(nombre).codigo(codigo).build();

      materiaRepository.save(materia);

      materias.put(codigo, materia);
    }
    return materias;
  }

  private void asociarCorrelativas(List<String[]> rows, Map<String, Materia> materias) {
    for (String[] row : rows) {
      String codigo = row[1].trim();
      Materia materia = materias.get(codigo);

      if (row.length > 2 && !row[2].isBlank()) {
        List<Materia> correlativas = Arrays.stream(row[2].split(",")).map(String::trim)
                                           .map(materias::get).filter(Objects::nonNull).toList();

        materia.setCorrelativas(correlativas);
        materiaRepository.save(materia);
      }
    }
  }
}
