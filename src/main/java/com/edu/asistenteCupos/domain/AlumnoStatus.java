package com.edu.asistenteCupos.domain;

import lombok.Data;

import java.util.List;

@Data
public class AlumnoStatus {
    private String dni;
    private String legajo;
    private String apellidos;
    private String nombres;
    private String mailDeContacto;
    private List<String> carreras;
    private Double coeficiente;
    private String carreraProblemaCupo;
    private Integer cantidadMateriasInscripto;

    // Materias específicas
    private List<String> introduccionProgramacion;
    private List<String> organizacionComputadoras;
    private List<String> matematica1;
    private List<String> programacionObjetos1;
    private List<String> basesDatos;
    private List<String> estructurasDatos;
    private List<String> programacionFuncional;
    private List<String> programacionConcurrente;
    private List<String> programacionObjetos2;
    private List<String> sistemasOperativos;
    private List<String> redesComputadoras;
    private List<String> laboratorioSistemasOperativosRedes;
    private List<String> introduccionBioinformatica;
    private List<String> construccionInterfacesUsuario;
    private List<String> estrategiasPersistencia;
    private List<String> desarrolloAplicaciones;
    private List<String> programacionObjetos3;
    private List<String> elementosIngenieriaSoftware;
    private List<String> seguridadInformatica;
    private List<String> introduccionDesarrolloVideojuegos;
    private List<String> seminarioBlockchain;
    private List<String> seminarioElectronicaArduino;
    private List<String> matematica2;
    private List<String> trabajoInsercionProfesional;
    private List<String> ingles2;
    private List<String> lenguajesFormalesAutomatas;
    private List<String> logicaProgramacion;
    private List<String> gestionProyectosSoftware;
    private List<String> arquitecturaSoftware2;
    private List<String> teoriaComputacion;
    private List<String> practicaDesarrolloSoftware;
    private List<String> arquitecturaComputadoras;
    private List<String> aspectosLegalesSociales;
    private List<String> redesNeuronales;
    private List<String> matematica3;
    private List<String> sistemasDistribuidos;
    private List<String> algoritmos;
    private List<String> ingles1;
    private List<String> analisisMatematico;
    private List<String> probabilidadEstadistica;
    private List<String> tallerTrabajoIntelectual;
    private List<String> tallerTrabajoUniversitario;

    // Historial de materias
    private Integer cantidadMateriasInscriptoAhora;
    private List<String> materiasCurso2024_1C;
    private List<String> materiasAprobo2024_1C;
    private List<String> materiasCurso2024_2C;
    private List<String> materiasAprobo2024_2C;
}
