package com.edu.asistenteCupos.repository.impl;

import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.repository.HistoriaAcademicaRepository;
import com.edu.asistenteCupos.repository.spring.HistoriaAcademicaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HistoriaAcademicaRepositoryImpl implements HistoriaAcademicaRepository {
    private final HistoriaAcademicaJpaRepository jpaRepository;

    @Override
    public HistoriaAcademica save(HistoriaAcademica historia) {
        return jpaRepository.save(historia);
    }

    @Override
    public List<HistoriaAcademica> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<HistoriaAcademica> findByCodigo(String legajo) {
        return jpaRepository.findByCodigo(legajo);
    }
}
