package com.edu.asistenteCupos.config.dev;

import com.edu.asistenteCupos.Utils.ClasspathResourceLoader;
import com.edu.asistenteCupos.domain.Estudiante;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.EstudianteRepository;
import com.edu.asistenteCupos.repository.HistoriaAcademicaRepository;
import com.edu.asistenteCupos.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class HistoriaAcademicaSeeder  {
    private final HistoriaAcademicaRepository historiaAcademicaRepository;
    private final EstudianteRepository alumnoRepository;
    private final MateriaRepository materiaRepository;
    private final ClasspathResourceLoader resourceLoader;
    String nombreCsv = "historiaAcademica.csv";
    public void cargarHistoriaAcademica(String nombreArchivo) throws Exception {
        List<String[]> rows = resourceLoader.leerCSV(nombreArchivo, "\\|");
        Map<String, HistoriaAcademica> historiaAcademicaMap = crearHistoriaAcademica(rows);
        AsignarHistoriaAlAlumno(rows, historiaAcademicaMap);
    }

    private void AsignarHistoriaAlAlumno(List<String[]> rows, Map<String, HistoriaAcademica> historiaAcademicaMap) {
        for (String[] row : rows.stream().skip(1).toList()) {
            String legajo = row[0].trim();
            String nombre = row[1].trim();
            String mail = row[2].trim();
            HistoriaAcademica historiaAcademica = historiaAcademicaMap.get(legajo);
            this.alumnoRepository.findByCodigo(legajo).ifPresentOrElse(
                    alumno -> {
                        alumno.setHistoriaAcademica(historiaAcademica);
                        this.alumnoRepository.save(alumno);
                    },
                    () -> {
                        Estudiante estudiante = Estudiante.builder()
                                .legajo(legajo)
                                .nombre(nombre)
                                .mail(mail)
                                .historiaAcademica(historiaAcademica)
                                .build();
                        this.alumnoRepository.save(estudiante);
                    }
            );
        }
    }

    private Map<String, HistoriaAcademica> crearHistoriaAcademica(List<String[]> rows) {
        Map<String, HistoriaAcademica> historiaAcademicaHashMap = new HashMap<>();
        for (String[] row : rows.stream().skip(1).toList()) {
            String legajo = row[0].trim();
            String nombre = row[1].trim();
            String mail = row[2].trim();
            int insc3 = Integer.parseInt(row[3].trim());
            int inscAct = Integer.parseInt(row[4].trim());
            int aprobUlt = Integer.parseInt(row[5].trim());
            int inscTot = Integer.parseInt(row[6].trim());
            int aprobTot = Integer.parseInt(row[7].trim());
            int restantes = Integer.parseInt(row[8].trim());
            String correlativas = row[9].trim();
            String[] anotadas = row[10].trim().split(",");

            List<Materia> materiasAnotadas = Arrays.stream(anotadas).map(materia->this.materiaRepository.findByCodigo(materia).orElseThrow(()->new RuntimeException("No se encontro la materia con el codigo: "+materia)))
                    .toList();
            HistoriaAcademica historiaAcademica = HistoriaAcademica.builder().legajo(legajo)
                    .insc3(insc3)
                    .inscAct(inscAct)
                    .aprobUlt(aprobUlt)
                    .inscTot(inscTot)
                    .aprobTot(aprobTot)
                    .restantes(restantes)
                    .correlativas(correlativas)
                    .anotadas(materiasAnotadas)
                    .build();

            historiaAcademicaRepository.save(historiaAcademica);

            historiaAcademicaHashMap.put(legajo, historiaAcademica);
        }
        return historiaAcademicaHashMap;
    }
    @Bean
    @Order(3)
    @Profile("dev")
    CommandLineRunner runComisionSeeder() {
        return args -> cargarHistoriaAcademica(nombreCsv);
    }
}
