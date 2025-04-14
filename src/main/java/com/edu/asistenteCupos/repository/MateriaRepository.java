package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaRepository {
  Materia save(Materia materia);

  List<Materia> findAll();

  Optional<Materia> findByCodigo(String codigoMateria);
}

