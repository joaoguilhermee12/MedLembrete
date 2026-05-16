package com.joaoguilhermee.MedLembrete.Model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class DisifyResponseDTO {
    private boolean format;
    private boolean disposable;
    private boolean dns;

}