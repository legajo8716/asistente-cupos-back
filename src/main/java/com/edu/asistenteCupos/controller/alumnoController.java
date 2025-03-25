package com.edu.asistenteCupos.controller;

import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/alumnos")
public class alumnoController {
    @Autowired
    AlumnoService alumnoService;
    @GetMapping
    public List<Alumno> getAlumnos(){
        return alumnoService.getAlumnos();
    }

}
