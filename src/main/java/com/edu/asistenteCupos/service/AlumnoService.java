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
        String prompsIngeniering = "Asigná cupos de materias a estudiantes según prioridad. Datos disponibles: cupos por materia, inscripciones últimos 3 cuatrimestres, inscripciones actuales, aprobadas último año, materia solicitada, totales de inscripciones y aprobadas, materias para graduarse, correlativas. " +
                "Criterios (prioridad 0-100): menos materias para graduarse ? más prioridad. Sin materias actuales ? prioridad máxima. Una sola materia ? alta prioridad. Pocas aprobadas pero inscripto recientemente ? más prioridad. Pocas aprobadas recientes ? más necesidad. " +
                "Puede cursar sin correlativas solo si le faltan 1 o 2 materias para egresar. No considerar superposición horaria si no hay datos. " +
                "Devolver la lista con este formato: [{\"alumno\": \"Nombre\", \"materia\": \"Materia\", \"prioridad\": 0-100, \"cupoAsignado\": true|false, \"explicacion\": \"Motivo\", \"cumple con las correlativas\": \"Sí\"|\"No. Modalidad libre\" }]. " +
                "Evaluá, asigná y explicá cada decisión con claridad. solo quiero que tu respuesta sea la lista de json sin nada mas. Los datos son: ";




        String call = chatModel.call(prompsIngeniering+userInput);


        // String call="";
       // ObjectMapper mapper = new ObjectMapper();
        //List<AsignacionCupoDto> asignaciones = mapper.readValue(call, new TypeReference<List<AsignacionCupoDto>>() {});


        logger.info("User input: " + userInput);
        logger.info("call: " + call);
        return call;
    }



}
