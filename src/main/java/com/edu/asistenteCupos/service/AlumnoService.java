package com.edu.asistenteCupos.service;

import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.repository.AlumnoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AlumnoService {
  private final OpenAiChatModel chatModel;

  Logger logger = Logger.getLogger(AlumnoService.class.getName());

  @Autowired
  AlumnoRepository alumnoRepository;

  public List<Alumno> getAlumnos() {
    return this.alumnoRepository.findAll();
  }

  public String consultar(String userInput) throws JsonProcessingException {
    String prompsIngeniering =
      "Asign� cupos de materias a estudiantes seg�n prioridad. Datos disponibles: cupos por materia, inscripciones �ltimos 3 cuatrimestres, inscripciones actuales, aprobadas �ltimo a�o, materia solicitada, totales de inscripciones y aprobadas, materias para graduarse, correlativas. " +
        "Criterios (prioridad 0-100): menos materias para graduarse ? m�s prioridad. Sin materias actuales ? prioridad m�xima. Una sola materia ? alta prioridad. Pocas aprobadas pero inscripto recientemente ? m�s prioridad. Pocas aprobadas recientes ? m�s necesidad. " +
        "Puede cursar sin correlativas solo si le faltan 1 o 2 materias para egresar. No considerar superposici�n horaria si no hay datos. " +
        "Devolver la lista con este formato: [{\"alumno\": \"Nombre\", \"materia\": \"Materia\", \"prioridad\": 0-100, \"cupoAsignado\": true|false, \"explicacion\": \"Motivo\", \"cumple con las correlativas\": \"S�\"|\"No. Modalidad libre\" }]. " +
        "Evalu�, asign� y explic� cada decisi�n con claridad. solo quiero que tu respuesta sea la lista de json sin nada mas. Los datos son: ";

    String call = chatModel.call(prompsIngeniering + userInput);

    logger.info("User input: " + userInput);
    logger.info("call: " + call);
    return call;
  }
}
