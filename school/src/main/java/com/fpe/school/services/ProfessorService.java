package com.fpe.school.services;

import com.fpe.school.entities.Professor;
import com.fpe.school.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public List<Professor> buscarPorNome(String nome) {
        return professorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizar(Long id, Professor professorAtualizado) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(professorAtualizado.getNome());
            professor.setIdade(professorAtualizado.getIdade());
            professor.setGenero(professorAtualizado.getGenero());
            professor.setAltura(professorAtualizado.getAltura());
            professor.setEntidade(professorAtualizado.getEntidade());
            professor.setMateria(professorAtualizado.getMateria());
            professor.setEficiencia(professorAtualizado.getEficiencia());
            professor.setHabilidades(professorAtualizado.getHabilidades());
            return professorRepository.save(professor);
        }).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }
}
