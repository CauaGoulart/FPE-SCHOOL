package com.fpe.school.services;

import com.fpe.school.entities.Aluno;
import com.fpe.school.entities.Nota;
import com.fpe.school.repositories.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public List<Nota> listarTodas() {
        return notaRepository.findAll();
    }

    public List<Nota> buscarPorAluno(Aluno aluno) {
        return notaRepository.findByAluno(aluno);
    }

    public Optional<Nota> buscarPorId(Long id) {
        return notaRepository.findById(id);
    }

    public Nota salvar(Nota nota) {
        return notaRepository.save(nota);
    }

    public void deletar(Long id) {
        notaRepository.deleteById(id);
    }
}
