package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.Comision;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ComisionRepositoryImpl implements ComisionRepository {
  private final ComisionJpaRepository jpaRepository;

  @Override
  public Comision save(Comision comision) {
    return jpaRepository.save(comision);
  }

  @Override
  public Optional<Comision> findById(String id) {
    return jpaRepository.findById(id);
  }

  @Override
  public List<Comision> findAll() {
    return jpaRepository.findAll();
  }
}
