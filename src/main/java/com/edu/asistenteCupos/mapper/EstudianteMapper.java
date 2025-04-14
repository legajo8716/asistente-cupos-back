package com.edu.asistenteCupos.mapper;

import com.edu.asistenteCupos.Utils.dto.EstudianteCSV;
import com.edu.asistenteCupos.Utils.dto.PeticionInscriptionDTO;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", uses = HistoriaAcademicaMapper.class)
public interface EstudianteMapper {
}
