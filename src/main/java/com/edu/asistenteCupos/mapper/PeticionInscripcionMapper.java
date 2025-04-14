package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.Utils.dto.HistoriaAcademicaDTO;
import com.edu.asistenteCupos.Utils.dto.PeticionInscriptionDTO;
import com.edu.asistenteCupos.Utils.dto.PeticionesInscripcionDTO;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.domain.PeticionInscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeticionInscripcionMapper {

    List<PeticionInscripcion> toPeticionInscripcionList(List<PeticionInscriptionDTO> peticiones);

    @Mapping(source = "nombre", target = "estudiante.nombre")
    @Mapping(source = "legajo", target = "estudiante.legajo")
    @Mapping(source = "historiaAcademica", target = "estudiante.historiaAcademica")
    @Mapping(source = "correlativa", target = "cumpleCorrelativa")
    PeticionInscripcion toPeticionInscripcion(PeticionInscriptionDTO dto);

    HistoriaAcademica toHistoriaAcademica(HistoriaAcademicaDTO dto);
}