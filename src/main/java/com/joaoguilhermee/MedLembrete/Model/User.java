package com.joaoguilhermee.MedLembrete.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @JsonProperty("CPF")
    @Column(unique = true)
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    @NotBlank(message = "CPF é obrigatório")
    private String CPF;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    private Boolean ativo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicine> medicines = new ArrayList<>();

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
}