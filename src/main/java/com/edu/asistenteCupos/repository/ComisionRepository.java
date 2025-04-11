package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.Comision;

import java.util.List;
import java.util.Optional;

public interface ComisionRepository {
  Comision save(Comision comision);

  Optional<Comision> findById(String id);

  List<Comision> findAll();
}

