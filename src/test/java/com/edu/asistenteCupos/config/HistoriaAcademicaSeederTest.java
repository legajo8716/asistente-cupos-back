package com.edu.asistenteCupos.config;

import com.edu.asistenteCupos.Utils.ClasspathResourceLoader;
import com.edu.asistenteCupos.config.dev.HistoriaAcademicaSeeder;
import com.edu.asistenteCupos.domain.HistoriaAcademica;
import com.edu.asistenteCupos.domain.Materia;
import com.edu.asistenteCupos.repository.EstudianteRepository;
import com.edu.asistenteCupos.repository.HistoriaAcademicaRepository;
import com.edu.asistenteCupos.repository.MateriaRepository;
import com.edu.asistenteCupos.repository.impl.memory.EstudianteRepositoryInMemory;
import com.edu.asistenteCupos.repository.impl.memory.HistoriaAcademicarRepositoryInMemory;
import com.edu.asistenteCupos.repository.impl.memory.MateriaRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoriaAcademicaSeederTest {

    @Test
    void cargaHistoriaAcademicaDesdeCSVDeTest() throws Exception {
        HistoriaAcademicaRepository historiaAcademicarRepositoryInMemory = new HistoriaAcademicarRepositoryInMemory();
        MateriaRepository materiaRepositoryInMemory = new MateriaRepositoryInMemory();
        EstudianteRepository estudianteRepositoryInMemory = new EstudianteRepositoryInMemory();
        ClasspathResourceLoader loader = new ClasspathResourceLoader();
        materiaRepositoryInMemory.save(Materia.builder().codigo("80005").nombre("Intro Test").build());
        materiaRepositoryInMemory.save(Materia.builder().codigo("80000").nombre("Avanzado Test").build());

        //legajo|nombre|mail|insc3| inscAct| aprobUlt| inscTot| aprobTot| restantes|correlativas|Anotadas
//100|pepe|pepe@gmail.com|2|0|1|6|3|5|Si|80005,80000

        List<Materia>materiasCorrelativas =List.of(
                Materia.builder().codigo("80005").build(),
                Materia.builder().codigo("80000").build()
        );
        historiaAcademicarRepositoryInMemory.save(HistoriaAcademica.builder().legajo("100").insc3(2)
                .inscAct(0).aprobUlt(1).inscTot(6).aprobTot(3).restantes(5).correlativas("Si").anotadas(materiasCorrelativas).build());
        HistoriaAcademicaSeeder historiaAcademicaSeeder = new HistoriaAcademicaSeeder(historiaAcademicarRepositoryInMemory, estudianteRepositoryInMemory, materiaRepositoryInMemory, loader);
        historiaAcademicaSeeder.cargarHistoriaAcademica("historiaAcademica_test.csv");

        List<HistoriaAcademica> all = historiaAcademicarRepositoryInMemory.findAll();
        assertThat(all).hasSize(2);
        HistoriaAcademica HistoriaAcademica = historiaAcademicarRepositoryInMemory.findByCodigo("100").orElseThrow();
        assertThat(HistoriaAcademica.getLegajo()).isEqualTo("100");
        assertThat(HistoriaAcademica.getCorrelativas()).isEqualTo("Si");

    }



}
