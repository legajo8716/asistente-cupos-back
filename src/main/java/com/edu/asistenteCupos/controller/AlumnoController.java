package com.edu.asistenteCupos.controller;

import com.edu.asistenteCupos.domain.Alumno;
import com.edu.asistenteCupos.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
class AlumnoController {
    @Autowired
    AlumnoService alumnoService;




    @GetMapping

    public List<Alumno> getAlumnos(){

        return alumnoService.getAlumnos();
    }
    @GetMapping("/consultar")
    public String consultar(@RequestParam String userInput){
      return  alumnoService.consultar(userInput);


    }

}
