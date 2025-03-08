package com.fpe.school.repositories;

import com.fpe.school.entities.Aluno;
import com.fpe.school.entities.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    List<Presenca> findByAluno(Aluno aluno);
    List<Presenca> findByData(LocalDate data);
}
