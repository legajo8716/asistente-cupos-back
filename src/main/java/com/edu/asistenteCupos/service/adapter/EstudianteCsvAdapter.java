package com.edu.asistenteCupos.service.adapter;

import com.edu.asistenteCupos.Utils.dto.EstudianteCSV;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.PeticionInscripcion;
import com.edu.asistenteCupos.mapper.EstudianteMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstudianteCsvAdapter {
    private final EstudianteMapper estudianteMapper;

    public List<Estudiante> convertir(MultipartFile archivoCsv) {
        List<EstudianteCSV> estudiantesCSV;
        try {
            estudiantesCSV = new CsvToBeanBuilder<EstudianteCSV>(
                    new InputStreamReader(archivoCsv.getInputStream(), StandardCharsets.UTF_8))
                    .withType(EstudianteCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo CSV: " + e.getMessage());
        }

//        return estudianteMapper.toDomainListFromCSV(estudiantesCSV);
        return null;
    }

    public List<PeticionInscripcion> convertirP(MultipartFile file) {
        return null;
    }
}
