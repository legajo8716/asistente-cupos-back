package com.edu.asistenteCupos.service;

import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    @Autowired
    AlumnoRepository alumnoRepository;
    public List<Alumno> getAlumnos() {
       return this.alumnoRepository.findAll();
    }
}
