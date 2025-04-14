package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.controller.dto.HistoriaAcademicaDTO;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoriaAcademicaMapper {
  HistoriaAcademica toDomain(HistoriaAcademicaDTO dto);
  HistoriaAcademicaDTO toDTO(HistoriaAcademica domain);
}
