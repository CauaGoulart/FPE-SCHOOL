package com.fpe.school.controllers;

import com.fpe.school.entities.Aluno;
import com.fpe.school.entities.Presenca;
import com.fpe.school.entities.Professor;
import com.fpe.school.services.AlunoService;
import com.fpe.school.services.PresencaService;
import com.fpe.school.services.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/presencas")
public class PresencaController {

    private final PresencaService presencaService;
    private final ProfessorService professorService;
    private final AlunoService alunoService;

    public PresencaController(PresencaService presencaService, AlunoService alunoService, ProfessorService professorService) {
        this.presencaService = presencaService;
        this.alunoService = alunoService;
        this.professorService = professorService;
    }

    @GetMapping("/registrar")
    public String exibirFormularioPresenca(Model model) {
        List<Aluno> alunos = alunoService.listarTodos();
        List<Professor> professores = professorService.listarTodos(); // Carrega os professores

        model.addAttribute("alunos", alunos);
        model.addAttribute("professores", professores); // Adiciona professores ao modelo

        return "registrar-presenca";
    }

    @PostMapping("/salvar")
    public String salvarPresenca(
            @RequestParam Long alunoId,
            @RequestParam Long professorId,
            @RequestParam String materia,
            @RequestParam Boolean presente) {

        Optional<Aluno> aluno = alunoService.buscarPorId(alunoId);
        Optional<Professor> professor = professorService.buscarPorId(professorId);

        if (aluno.isEmpty() || professor.isEmpty()) {
            return "redirect:/presencas/registrar?erro=entidade_nao_encontrada";
        }

        Presenca presenca = new Presenca();
        presenca.setAluno(aluno.get());
        presenca.setProfessor(professor.get());
        presenca.setMateria(materia);
        presenca.setData(LocalDate.now());
        presenca.setPresente(presente);

        presencaService.registrarPresenca(presenca);
        return "redirect:/presencas/registrar?sucesso=presenca_registrada";
    }
}
