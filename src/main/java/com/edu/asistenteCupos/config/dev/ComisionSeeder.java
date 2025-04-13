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
    List<String[]> rows = resourceLoader.leerCSV(nombreArchivo, "\\|");

    for (String[] row : rows) {
      String comisionId = row[0];
      String codigoMateria = row[1];
      String horario = row[2];
      int cupo = Integer.parseInt(row[3]);

      Optional<Materia> materiaOpt = materiaRepository.findByCodigo(codigoMateria);

      if (materiaOpt.isPresent()) {
        Materia materia = materiaOpt.get();

        Comision comision = Comision.builder().id(comisionId).materia(materia).horario(horario)
                                    .cupo(cupo).build();

        comisionRepository.save(comision);
      } else {
        System.err.printf("Materia con código [%s] no encontrada para la comisión [%s - %n]",
          codigoMateria, comisionId);
      }
    }
  }

  @Bean
  @Order(2)
  @Profile("dev")
  CommandLineRunner runComisionSeeder() {
    return args -> cargarComisiones(nombreCsv);
  }
}
