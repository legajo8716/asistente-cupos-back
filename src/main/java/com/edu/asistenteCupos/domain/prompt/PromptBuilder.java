package com.edu.asistenteCupos.domain.prompt;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Materia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromptBuilder {

  private final StringBuilder prompt = new StringBuilder();
  private List<Materia> materias = new ArrayList<>();
  private List<Comision> comisiones = new ArrayList<>();
  private String inputUsuario = "";

  private PromptBuilder() {}

  public static PromptBuilder nuevo() {
    return new PromptBuilder();
  }

  public PromptBuilder conMaterias(List<Materia> materias) {
    this.materias = materias;
    return this;
  }

  public PromptBuilder conComisiones(List<Comision> comisiones) {
    this.comisiones = comisiones;
    return this;
  }

  public PromptBuilder conPeticionesDeInscripcion(String inputUsuario) {
    this.inputUsuario = inputUsuario;
    return this;
  }

  public String construir() {
    construirSeccionMaterias();
    construirSeccionComisiones();
    construirSeccionDePeticiones();

    return prompt.toString();
  }

  private void construirSeccionMaterias() {
    if (!materias.isEmpty()) {
      prompt.append("## INSTRUCCIÓN PARA MODELO DE LENGUAJE ##\n");
      prompt.append("OBJETIVO: Mostrar las materias disponibles y sus correlativas\n");
      prompt.append("FORMATO REQUERIDO: Lista con viñetas\n");
      prompt.append("ESTRUCTURA: - [Nombre de Materia] ([Código]) | Correlativas: [Códigos separados por coma]\n");
      prompt.append("CONTEXTO: Las materias listadas están disponibles para inscripción\n\n");

      prompt.append("RESULTADO ESPERADO:\n");
      for (Materia materia : materias) {
        prompt.append("- ")
              .append(materia.getNombre())
              .append(" (").append(materia.getCodigo()).append(")");
        if (!materia.getCorrelativas().isEmpty()) {
          prompt.append(" | Correlativas: ")
                .append(materia.getCorrelativas().stream()
                               .map(Materia::getCodigo)
                               .collect(Collectors.joining(", ")));
        }
        prompt.append("\n");
      }
      prompt.append("\n");
    }
  }

  private void construirSeccionComisiones() {
    if (!comisiones.isEmpty()) {
      prompt.append("## INSTRUCCIÓN PARA MODELO DE LENGUAJE ##\n");
      prompt.append("OBJETIVO: Mostrar las comisiones disponibles y sus cupos\n");
      prompt.append("FORMATO REQUERIDO: Lista con viñetas\n");
      prompt.append("ESTRUCTURA: - [ID_COMISIÓN] | [Código Materia] | [Horario] | Cupo: [NÚMERO]\n");
      prompt.append("CONTEXTO: Cada comisión corresponde a una materia y tiene un cupo limitado\n\n");

      prompt.append("RESULTADO ESPERADO:\n");
      for (Comision comision : comisiones) {
        prompt.append("- ")
              .append(comision.getId()).append(" | ")
              .append(comision.getMateria().getCodigo()).append(" | ")
              .append(comision.getHorario()).append(" | ")
              .append("Cupo: ").append(comision.getCupo())
              .append("\n");
      }
      prompt.append("\n");
    }
  }

  private void construirSeccionDePeticiones() {
    if (!inputUsuario.isEmpty()) {
      prompt.append("## CONSULTA DEL USUARIO ##\n");
      prompt.append("Este es el pedido del alumno:\n");
      prompt.append(inputUsuario).append("\n");
    }
  }
}
