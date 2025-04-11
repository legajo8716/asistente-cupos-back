package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.Comision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ComisionRepository extends JpaRepository<Comision, String> {}
