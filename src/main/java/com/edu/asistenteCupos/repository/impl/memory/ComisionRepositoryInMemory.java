package com.edu.asistenteCupos.repository.impl.memory;

import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.repository.ComisionRepository;

import java.util.*;

public class ComisionRepositoryInMemory implements ComisionRepository {
  private final Map<String, Comision> data = new HashMap<>();

  @Override
  public Comision save(Comision comision) {
    data.put(comision.getCodigo(), comision);
    return comision;
  }

  @Override
  public Optional<Comision> findById(String id) {
    return Optional.ofNullable(data.get(id));
  }

  @Override
  public List<Comision> findAll() {
    return new ArrayList<>(data.values());
  }
}
