package com.joaoguilhermee.MedLembrete.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joaoguilhermee.MedLembrete.Model.DTO.UserSummaryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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

    private Integer dosesTomadas = 0;

    @JsonIgnore
    @Schema(hidden = true)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(implementation = UserSummaryDTO.class)
    public UserSummaryDTO getUserDTO() {
        if (user == null) return null;
        return new UserSummaryDTO(user.getId(), user.getNome());
    }
}