package com.fpe.school.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

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
    private String entidade; // Ex: Humano, Mago, Elfo, etc.

    @Column(nullable = false)
    private String materia; // Matéria que o professor ensina

    @ElementCollection
    @CollectionTable(name = "professor_eficiencia", joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "nivel")
    private List<String> eficiencia; // Ex: ["Muito Bom", "Excelente", "Avançado"]

    @ElementCollection
    @CollectionTable(name = "professor_habilidades", joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "habilidade")
    private List<String> habilidades; // Lista de habilidades do professor
}
