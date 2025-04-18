package com.edu.asistenteCupos.config.dev;

import com.edu.asistenteCupos.Utils.ClasspathResourceLoader;
import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.ComisionRepository;
import com.edu.asistenteCupos.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ComisionSeeder {

  private final ComisionRepository comisionRepository;
  private final MateriaRepository materiaRepository;
  private final ClasspathResourceLoader resourceLoader;
  private final String nombreCsv = "comisiones.csv";

  public void cargarComisiones(String nombreArchivo) throws Exception {
    if (!comisionRepository.findAll().isEmpty()) {
      return;
    }
    List<String[]> rows = resourceLoader.leerCSV(nombreArchivo, "\\|");

    for (String[] row : rows.stream().skip(1).toList()) {
      String codigoMateria = row[0];
      String codigoComision = row[1];
      String horario = row[2];
      int cupo = Integer.parseInt(row[3]);

      Optional<Materia> materiaOpt = materiaRepository.findByCodigo(codigoMateria);

      if (materiaOpt.isPresent()) {
        Materia materia = materiaOpt.get();

        Comision comision = Comision.builder().codigo(codigoComision).materia(materia)
                                    .horario(horario).cupo(cupo).build();

        comisionRepository.save(comision);
      } else {
        System.err.printf("Materia con código [%s] no encontrada para la comisión [%s - %n]",
          codigoMateria, codigoComision);
      }
    }
  }

  @Bean
  @Order(2)
  @Profile({"dev", "test"})
  CommandLineRunner runComisionSeeder() {
    return args -> cargarComisiones(nombreCsv);
  }
}
