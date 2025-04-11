package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.Utils.dto.EstudianteDTO;
import com.edu.asistenteCupos.Utils.dto.HistoriaAcademicaDTO;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {
  EstudianteMapper INSTANCE = Mappers.getMapper(EstudianteMapper.class);

  Estudiante toDomain(EstudianteDTO dto);

  EstudianteDTO toDTO(Estudiante domain);

  HistoriaAcademica toDomain(HistoriaAcademicaDTO dto);

  HistoriaAcademicaDTO toDTO(HistoriaAcademica domain);

  List<Estudiante> toDomainList(List<EstudianteDTO> dtoList);

  List<EstudianteDTO> toDTOList(List<Estudiante> domainList);
}
