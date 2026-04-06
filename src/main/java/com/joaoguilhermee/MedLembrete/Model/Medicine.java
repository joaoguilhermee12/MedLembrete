package com.joaoguilhermee.MedLembrete.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_medicine")
@NoArgsConstructor
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Nome do medicamento é obrigatório")
    private String nome;

    @NotBlank(message = "Horário é obrigatório")
    private String horario;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate diaInicio;

    @NotNull(message = "Data final é obrigatória")
    private LocalDate diaFinal;

    @NotNull(message = "Quantidade de doses é obrigatória")
    private Integer doses;

    @NotNull(message = "Doses por dia é obrigatório")
    private Integer dosesPorDia;

    private boolean tomado = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"email", "senha", "CPF", "ativo"})
    private User user;
}