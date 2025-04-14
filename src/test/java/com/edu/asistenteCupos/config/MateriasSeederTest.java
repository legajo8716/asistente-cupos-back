package com.edu.asistenteCupos.config;

import com.edu.asistenteCupos.Utils.ClasspathResourceLoader;
import com.edu.asistenteCupos.config.dev.MateriasSeeder;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.MateriaRepository;
import com.edu.asistenteCupos.repository.impl.memory.MateriaRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MateriasSeederTest {
  @Test
  void cargaMateriasDesdeCSVDeTest() throws Exception {
    MateriaRepository materiaRepo = new MateriaRepositoryInMemory();
    ClasspathResourceLoader loader = new ClasspathResourceLoader();

    MateriasSeeder seeder = new MateriasSeeder(materiaRepo, loader);
    seeder.cargarMaterias("materias_test.csv");

    List<Materia> materias = materiaRepo.findAll();
    assertThat(materias).hasSize(2);
    Materia test101 = materiaRepo.findByCodigo("TEST101").orElseThrow();
    assertThat(test101.getNombre()).isEqualTo("Introducción a los Testeos");
    assertThat(test101.getCorrelativas()).isEmpty();
  }
}
