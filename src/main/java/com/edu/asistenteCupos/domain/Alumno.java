package com.edu.asistenteCupos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Alumno {
  @Id
  @GeneratedValue
  private String id;
  private String nombre;

}
