package com.edu.asistenteCupos.config;

import com.edu.asistenteCupos.config.dev.ComisionSeeder;
import com.edu.asistenteCupos.config.dev.MateriasSeeder;
import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.repository.ComisionRepository;
import com.edu.asistenteCupos.repository.MateriaRepository;
import com.edu.asistenteCupos.repository.memory.ComisionRepositoryInMemory;
import com.edu.asistenteCupos.repository.memory.MateriaRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ComisionSeederTest {
  @Test
  void dadoUnCSVDeComisionesYMateriasSeGuardanEnLaBaseDeDatos() throws Exception {
    ComisionRepository comisionRepo = new ComisionRepositoryInMemory();
    MateriaRepository materiaRepo = new MateriaRepositoryInMemory();
    ComisionSeeder comisionSeeder = new ComisionSeeder(comisionRepo, materiaRepo);
    MateriasSeeder materiaSeeder = new MateriasSeeder(materiaRepo);

    materiaSeeder.cargarMaterias("materias.csv");
    comisionSeeder.cargarComisiones("comisiones.csv");


    List<Comision> comisiones = comisionRepo.findAll();
    assertThat(comisiones).extracting(Comision::getId)
                          .containsExactlyInAnyOrder("CI102COM1", "CI103COM1", "CI103COM2",
                            "NA301COM1", "NA301COM2", "NA301COM3");
    Comision comision = comisiones.stream().filter(c -> c.getId().equals("CI103COM2")).findFirst()
                                  .orElseThrow();

    assertThat(comision.getHorario()).isEqualTo("Miércoles 09:00 a 11:59");
    assertThat(comision.getCupo()).isEqualTo(6);
  }
}