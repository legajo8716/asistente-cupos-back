package com.edu.asistenteCupos.config;


import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.MateriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class DataSeederTest {

  @Autowired
  private MateriaRepository materiaRepository;

  @Test
  void dadoUnCSVSeCarganLasMateriasEnLaBaseDeDatos() {
    List<Materia> materias = materiaRepository.findAll();
    assertThat(materias).isNotEmpty();
    assertThat(materias).extracting(Materia::getCodigo)
                        .containsExactlyInAnyOrder("MAT101", "MAT102", "MAT103");
  }
}
