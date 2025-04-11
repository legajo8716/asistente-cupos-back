package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.Materia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MateriaRepositoryImpl implements MateriaRepository {
  private final MateriaJpaRepository jpaRepository;

  @Override
  public Materia save(Materia materia) {
    return jpaRepository.save(materia);
  }

  @Override
  public Optional<Materia> findById(String id) {
    return jpaRepository.findById(id);
  }

  @Override
  public List<Materia> findAll() {
    return jpaRepository.findAll();
  }

  @Override
  public Optional<Materia> findByCodigo(String codigoMateria) {
    return jpaRepository.findByCodigo(codigoMateria);
  }
}
