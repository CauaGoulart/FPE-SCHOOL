package com.fpe.school.services;

import com.fpe.school.entities.Aluno;
import com.fpe.school.entities.Presenca;
import com.fpe.school.repositories.PresencaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PresencaService {

    private final PresencaRepository presencaRepository;

    public PresencaService(PresencaRepository presencaRepository) {
        this.presencaRepository = presencaRepository;
    }

    public List<Presenca> listarTodas() {
        return presencaRepository.findAll();
    }

    public List<Presenca> listarPorAluno(Aluno aluno) {
        return presencaRepository.findByAluno(aluno);
    }

    public List<Presenca> listarPorData(LocalDate data) {
        return presencaRepository.findByData(data);
    }

    public Presenca registrarPresenca(Presenca presenca) {
        return presencaRepository.save(presenca);
    }
}
