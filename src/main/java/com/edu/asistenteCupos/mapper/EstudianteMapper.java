package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.Utils.dto.EstudianteCSV;
import com.edu.asistenteCupos.Utils.dto.EstudianteDTO;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", uses = HistoriaAcademicaMapper.class)
public interface EstudianteMapper {
  Estudiante toDomain(EstudianteDTO dto);

  EstudianteDTO toDTO(Estudiante domain);

  List<Estudiante> toDomainList(List<EstudianteDTO> dtoList);

  List<EstudianteDTO> toDTOList(List<Estudiante> domainList);
  @Mapping(source = "nombre", target = "nombre")
  @Mapping(source = "legajo", target = "legajo", qualifiedByName = "intToString")
  @Mapping(source = "materia", target = "materia")
  @Mapping(source = "comisiones", target = "comisiones", qualifiedByName = "stringToList")
  @Mapping(source = "correlativa", target = "correlativa", qualifiedByName = "correlativaToBoolean")
  @Mapping(target = "historiaAcademica", expression = "java(toHistoriaAcademica(estudianteCSV))")
  Estudiante toDomainFromCSV(EstudianteCSV estudianteCSV);

  List<Estudiante> toDomainListFromCSV(List<EstudianteCSV> estudianteCSVList);


  @Named("stringToList")
  default List<String> stringToList(String comisiones) {
    return Arrays.stream(comisiones.split(","))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .toList();
  }

  @Named("correlativaToBoolean")
  default boolean correlativaToBoolean(String correlativa) {
    return "si".equalsIgnoreCase(correlativa.trim());
  }

  @Named("intToString")
  default String intToString(int valor) {
    return String.valueOf(valor);
  }

  default HistoriaAcademica toHistoriaAcademica(EstudianteCSV csv) {
    return new HistoriaAcademica(
            csv.getInsc3(),
            csv.getInscAct(),
            csv.getAprobUlt(),
            csv.getInscTot(),
            csv.getAprobTot(),
            csv.getRestantes()
    );
  }

}
