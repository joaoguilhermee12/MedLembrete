package com.joaoguilhermee.MedLembrete.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserStatusDTO {
    private Long id;
    private String nome;
    private Boolean ativo;
}