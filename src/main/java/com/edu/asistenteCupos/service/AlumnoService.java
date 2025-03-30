package com.edu.asistenteCupos.service;

import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.repository.AlumnoRepository;
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
    public String consultar(String userInput){
        String call = chatModel.call(userInput);
        logger.info("User input: " + userInput);
        logger.info("call: " + call);
        return call;
    }



}
