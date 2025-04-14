package com.edu.asistenteCupos.service.factory;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.ComisionRepository;
import com.edu.asistenteCupos.repository.MateriaRepository;
import com.edu.asistenteCupos.repository.memory.ComisionRepositoryInMemory;
import com.edu.asistenteCupos.repository.memory.MateriaRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromptFactoryTest {
  private PromptFactory promptFactory;

  @BeforeEach
  void setUp() {
    MateriaRepository materiaRepository = new MateriaRepositoryInMemory();
    ComisionRepository comisionRepository = new ComisionRepositoryInMemory();

    Materia materia = new Materia(1L, "Algoritmos", "CI101", List.of());
    materiaRepository.save(materia);

    Comision comision = new Comision("CI101COM1", "Lunes 10:00 a 12:00", 30, materia);
    comisionRepository.save(comision);

    promptFactory = new PromptFactory(materiaRepository, comisionRepository);
  }

//  @Test
//  void alCrearElPromptCorrectamenteContieneTodosLosCriteriosDePrioridad() {
//    promptFactory.setCriteriosFileName("test-criterios.txt");
//
//    List<Estudiante> peticionesDeInscripcion = new ArrayList<>();
//    Prompt prompt = promptFactory.crearPrompt(peticionesDeInscripcion);
//    List<Message> messages = prompt.getInstructions();
//
//    assertThat(messages.get(0).getText()).contains("- Un criterio");
//    assertThat(messages.get(0).getText()).contains("- Otro criterio");
//    assertThat(messages.get(0).getText()).contains("- Mas criterios");
//  }
//
//  @Test
//  void alCrearElPromptVerificaQueContieneInstruccionesFijasDelTemplate() {
//    promptFactory.setSystemMessageFileName("test-system-message.txt");
//
//    List<Estudiante> peticionesDeInscripcion = new ArrayList<>();
//    Prompt prompt = promptFactory.crearPrompt(peticionesDeInscripcion);
//    String systemMessage = prompt.getInstructions().get(0).getText();
//
//    assertThat(systemMessage).contains("## UNA INSTRUCCIÓN IMPORTANTE ##");
//    assertThat(systemMessage).contains("## UN MENSAJE IMPORTANTE##");
//  }
}
