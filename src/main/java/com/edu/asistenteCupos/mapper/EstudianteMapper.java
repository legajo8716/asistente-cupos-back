package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.Utils.dto.EstudianteDTO;
import com.edu.asistenteCupos.domain.Estudiante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = HistoriaAcademicaMapper.class)
public interface EstudianteMapper {
  Estudiante toDomain(EstudianteDTO dto);

  EstudianteDTO toDTO(Estudiante domain);

  List<Estudiante> toDomainList(List<EstudianteDTO> dtoList);

  List<EstudianteDTO> toDTOList(List<Estudiante> domainList);
}
