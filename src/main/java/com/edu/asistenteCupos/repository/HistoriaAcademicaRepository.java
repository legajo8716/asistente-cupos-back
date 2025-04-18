package com.edu.asistenteCupos.repository;

import com.edu.asistenteCupos.domain.HistoriaAcademica;

import java.util.List;
import java.util.Optional;

public interface HistoriaAcademicaRepository  {
    HistoriaAcademica save(HistoriaAcademica historia);

    List<HistoriaAcademica> findAll();

    Optional<HistoriaAcademica> findByIdHistoriaAcademica(Long idHistoriaAcademica);
}
