package com.joaoguilhermee.MedLembrete.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_medicine")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String horario;
    private LocalDate diaInicio;
    private LocalDate diaFinal;
    private Integer doses;
    private Integer dosesPorDia;
    private boolean tomado;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
