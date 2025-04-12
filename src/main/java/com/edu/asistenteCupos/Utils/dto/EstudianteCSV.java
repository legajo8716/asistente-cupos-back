package com.edu.asistenteCupos.Utils.dto;

import com.opencsv.bean.CsvBindByName;

public class EstudianteCSV {
    @CsvBindByName
    private String nombre;
    @CsvBindByName
    private int legajo;
    @CsvBindByName
    private String materia;
    @CsvBindByName
    private String comisiones;
    @CsvBindByName
    private int insc3;
    @CsvBindByName
    private int inscAct;
    @CsvBindByName
    private int aprobUlt;
    @CsvBindByName
    private int inscTot;
    @CsvBindByName
    private int aprobTot;
    @CsvBindByName
    private int restantes;
    @CsvBindByName
    private String correlativa;

    public EstudianteCSV() {}

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getLegajo() { return legajo; }
    public void setLegajo(int legajo) { this.legajo = legajo; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public String getComisiones() { return comisiones; }
    public void setComisiones(String comisiones) { this.comisiones = comisiones; }

    public int getInsc3() { return insc3; }
    public void setInsc3(int insc3) { this.insc3 = insc3; }

    public int getInscAct() { return inscAct; }
    public void setInscAct(int inscAct) { this.inscAct = inscAct; }

    public int getAprobUlt() { return aprobUlt; }
    public void setAprobUlt(int aprobUlt) { this.aprobUlt = aprobUlt; }

    public int getInscTot() { return inscTot; }
    public void setInscTot(int inscTot) { this.inscTot = inscTot; }

    public int getAprobTot() { return aprobTot; }
    public void setAprobTot(int aprobTot) { this.aprobTot = aprobTot; }

    public int getRestantes() { return restantes; }
    public void setRestantes(int restantes) { this.restantes = restantes; }

    public String getCorrelativa() { return correlativa; }
    public void setCorrelativa(String correlativa) { this.correlativa = correlativa; }
}
