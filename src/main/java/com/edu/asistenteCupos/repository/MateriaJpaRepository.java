package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateriaJpaRepository extends JpaRepository<Materia, String> {
  Optional<Materia> findByCodigo(String codigoMateria);
}
