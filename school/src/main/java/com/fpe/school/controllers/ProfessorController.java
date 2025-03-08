package com.fpe.school.controllers;

import com.fpe.school.entities.Professor;
import com.fpe.school.services.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> listarTodos() {
        return professorService.listarTodos();
    }

    @GetMapping("/listar")
    public String listarProfessores(Model model) {
        List<Professor> professores = professorService.listarTodos();
        model.addAttribute("professores", professores);
        return "listar-professores";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        Optional<Professor> professor = professorService.buscarPorId(id);
        return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Professor> buscarPorNome(@RequestParam String nome) {
        return professorService.buscarPorNome(nome);
    }

    @PostMapping("/salvar")
    public String salvarProfessor(@ModelAttribute Professor professor) {
        professorService.salvar(professor);
        return "redirect:/cadastro-professor?sucesso=true";
    }

    @GetMapping("/editar/{id}")
    public String editarProfessor(@PathVariable Long id, Model model) {
        Optional<Professor> professor = professorService.buscarPorId(id);
        if (professor.isEmpty()) {
            return "redirect:/professores/listar?erro=not_found";
        }
        model.addAttribute("professor", professor.get());
        return "editar-professor";
    }

    @PostMapping("/atualizar")
    public String atualizarProfessor(@ModelAttribute Professor professor) {
        professorService.atualizar(professor.getId(), professor);
        return "redirect:/professores/listar?sucesso=atualizado";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProfessor(@PathVariable Long id) {
        professorService.deletar(id);
        return "redirect:/professores/listar?sucesso=professor_excluido";
    }
}
