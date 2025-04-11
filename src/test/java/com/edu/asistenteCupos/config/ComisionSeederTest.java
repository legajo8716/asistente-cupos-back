package com.edu.asistenteCupos.config;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.repository.ComisionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class ComisionSeederTest {
  @Autowired
  private ComisionRepository comisionRepository;

  @Test
  void dadoUnCSVDeComisionesYMateriasSeGuardanEnLaBaseDeDatos() {
    List<Comision> comisiones = comisionRepository.findAll();

    assertThat(comisiones).extracting(Comision::getId)
                          .containsExactlyInAnyOrder("CI102COM1", "CI103COM1", "CI103COM2",
                            "NA301COM1", "NA301COM2", "NA301COM3");
    Comision comision = comisiones.stream().filter(c -> c.getId().equals("CI103COM2")).findFirst()
                                  .orElseThrow();

    assertThat(comision.getHorario()).isEqualTo("Miércoles 09:00 a 11:59");
    assertThat(comision.getCupo()).isEqualTo(6);
  }
}