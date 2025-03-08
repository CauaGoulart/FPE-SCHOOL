package com.fpe.school.controllers;

import com.fpe.school.entities.Aluno;
import com.fpe.school.entities.Professor;
import com.fpe.school.entities.Nota;
import com.fpe.school.services.AlunoService;
import com.fpe.school.services.ProfessorService;
import com.fpe.school.services.NotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;
    private final AlunoService alunoService;
    private final ProfessorService professorService;

    public NotaController(NotaService notaService, AlunoService alunoService, ProfessorService professorService) {
        this.notaService = notaService;
        this.alunoService = alunoService;
        this.professorService = professorService;
    }

    @GetMapping("/selecionar")
    public String selecionarAlunoParaNotas(Model model) {
        List<Aluno> alunos = alunoService.listarTodos();
        model.addAttribute("alunos", alunos);
        return "selecionar-aluno"; // Página para selecionar um aluno
    }

    @GetMapping("/aluno")
    public String listarNotasAluno(@RequestParam Long alunoId, Model model) {
        Optional<Aluno> aluno = alunoService.buscarPorId(alunoId);

        if (aluno.isEmpty()) {
            return "redirect:/notas/selecionar?erro=aluno_nao_encontrado"; // Se o aluno não existir, volta à seleção
        }

        List<Nota> notas = notaService.buscarPorAluno(aluno.get());

        model.addAttribute("aluno", aluno.get());
        model.addAttribute("notas", notas);
        return "listar-notas"; // Página que exibe as notas
    }

    @GetMapping("/cadastro-nota")
    public String formularioCadastroNota(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        model.addAttribute("professores", professorService.listarTodos());
        return "cadastro-nota";
    }

    @PostMapping("/salvar")
    public String salvarNota(@RequestParam Long alunoId, @RequestParam Long professorId,
                             @RequestParam String materia, @RequestParam Double nota) {

        Optional<Aluno> aluno = alunoService.buscarPorId(alunoId);
        Optional<Professor> professor = professorService.buscarPorId(professorId);

        if (aluno.isEmpty() || professor.isEmpty()) {
            return "redirect:/notas/cadastro-nota?erro=true";
        }

        notaService.salvar(criarNota(aluno.get(), professor.get(), materia, nota));
        return "redirect:/notas/cadastro-nota?sucesso=true";
    }

    private Nota criarNota(Aluno aluno, Professor professor, String materia, Double nota) {
        return new Nota(null, aluno, professor, materia, nota);
    }
}
