package com.fpe.school.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private String entidade;

    @ElementCollection
    @CollectionTable(name = "aluno_talentos", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "talento")
    private List<String> talentos; // Lista de talentos ou atividades

    @ElementCollection
    @CollectionTable(name = "aluno_habilidades", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "habilidade")
    private List<String> habilidades; // Lista de habilidades
}
