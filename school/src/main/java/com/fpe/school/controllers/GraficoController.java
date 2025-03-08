package com.fpe.school.controllers;

import com.fpe.school.entities.Nota;
import com.fpe.school.services.NotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GraficoController {

    private final NotaService notaService;

    public GraficoController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping("/graficos/desempenho")
    public String exibirGrafico(@RequestParam(required = false) String materia, Model model) {
        List<Nota> notas = notaService.listarTodas();

        // Obtendo todas as matérias disponíveis
        Set<String> materias = notas.stream()
                .map(Nota::getMateria)
                .collect(Collectors.toSet());

        // Filtrando notas pela matéria selecionada
        if (materia != null && !materia.isEmpty()) {
            notas = notas.stream()
                    .filter(n -> n.getMateria().equalsIgnoreCase(materia))
                    .collect(Collectors.toList());
        }

        // Agrupar notas por aluno e calcular a média
        Map<String, Double> mediasPorAluno = new HashMap<>();
        Map<String, Integer> contagemNotas = new HashMap<>();

        for (Nota nota : notas) {
            String alunoNome = nota.getAluno().getNome();
            mediasPorAluno.put(alunoNome, mediasPorAluno.getOrDefault(alunoNome, 0.0) + nota.getNota());
            contagemNotas.put(alunoNome, contagemNotas.getOrDefault(alunoNome, 0) + 1);
        }

        // Calcular a média
        for (String aluno : mediasPorAluno.keySet()) {
            mediasPorAluno.put(aluno, mediasPorAluno.get(aluno) / contagemNotas.get(aluno));
        }

        model.addAttribute("materias", materias);
        model.addAttribute("materiaSelecionada", materia);
        model.addAttribute("mediasPorAluno", mediasPorAluno);
        return "grafico-desempenho";
    }
}
