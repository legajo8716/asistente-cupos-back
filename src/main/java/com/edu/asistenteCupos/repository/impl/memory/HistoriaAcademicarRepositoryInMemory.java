package com.edu.asistenteCupos.repository.impl.memory;

import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.repository.HistoriaAcademicaRepository;

import java.util.*;

public class HistoriaAcademicarRepositoryInMemory implements HistoriaAcademicaRepository {
    private final Map<String, HistoriaAcademica> data = new HashMap<>();

    @Override
    public HistoriaAcademica save(HistoriaAcademica historia) {
        data.put(historia.getLegajo(), historia);
        return historia;
    }


    @Override
    public List<HistoriaAcademica> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<HistoriaAcademica> findByCodigo(String codigoMateria) {
        return data.get(codigoMateria) != null ? Optional.of(data.get(codigoMateria)) : Optional.empty();
    }
}

