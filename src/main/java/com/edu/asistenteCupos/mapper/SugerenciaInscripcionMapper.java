package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.controller.dto.SugerenciaInscripcionDto;
import com.edu.asistenteCupos.domain.Comision;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.domain.SugerenciaInscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface SugerenciaInscripcionMapper {
  @Mapping(source = ".", target = "estudiante", qualifiedByName = "mapEstudiante")
  @Mapping(source = ".", target = "comision", qualifiedByName = "mapComision")
  @Mapping(source = ".", target = "motivo", qualifiedByName = "mapMotivo")
  @Mapping(source = ".", target = "cupoAsignado", qualifiedByName = "mapCupoAsignado")
  @Mapping(source = ".", target = "prioridad", qualifiedByName = "mapPrioridad")
  SugerenciaInscripcion toSugerenciaInscripcion(Map<String, Object> jsonMap);

  @Mapping(source = "estudiante.nombre", target = "nombreEstudiante")
  @Mapping(source = "comision.id", target = "codigoComision")
  SugerenciaInscripcionDto toSugerenciaInscripcionDto(SugerenciaInscripcion sugerencia);

  List<SugerenciaInscripcionDto> toSugerenciaInscripcionDtoList(List<SugerenciaInscripcion> sugerencias);

  @Named("mapEstudiante")
  default Estudiante mapEstudiante(Map<String, Object> jsonMap) {
    if (jsonMap == null) {
      return null;
    }
    Estudiante estudiante = new Estudiante();
    Object estudianteValue = jsonMap.get("estudiante");
    if (estudianteValue instanceof String) {
      estudiante.setNombre((String) estudianteValue);
    } else {
      estudiante.setNombre(null);
    }
    return estudiante;
  }

  @Named("mapComision")
  default Comision mapComision(Map<String, Object> jsonMap) {
    if (jsonMap == null) {
      return null;
    }
    Comision comision = new Comision();
    Materia materia = new Materia();
    Object materiaValue = jsonMap.get("materia");
    if (materiaValue instanceof String) {
      materia.setNombre((String) materiaValue);
    } else {
      materia.setNombre(null);
    }
    comision.setMateria(materia);
    return comision;
  }

  @Named("mapMotivo")
  default String mapMotivo(Map<String, Object> jsonMap) {
    if (jsonMap == null) {
      return null;
    }
    Object motivoValue = jsonMap.get("motivo");
    if (motivoValue instanceof String) {
      return (String) motivoValue;
    } else {
      return null;
    }
  }

  @Named("mapCupoAsignado")
  default Boolean mapCupoAsignado(Map<String, Object> jsonMap) {
    if (jsonMap == null) {
      return null;
    }
    Object cupoAsignadoValue = jsonMap.get("cupoAsignado");
    if (cupoAsignadoValue instanceof Boolean) {
      return (Boolean) cupoAsignadoValue;
    } else {
      return null;
    }
  }

  @Named("mapPrioridad")
  default Integer mapPrioridad(Map<String, Object> jsonMap) {
    if (jsonMap == null) {
      return null;
    }
    Object prioridadValue = jsonMap.get("prioridad");
    if (prioridadValue instanceof Integer) {
      return (Integer) prioridadValue;
    } else {
      return null;
    }
  }
}