package com.joaoguilhermee.MedLembrete.Service.External;

import com.joaoguilhermee.MedLembrete.Model.DTO.DisifyResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmailValidationService {

    private final WebClient webClient;

    public EmailValidationService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://disify.com/api")
                .build();
    }

    public boolean isEmailValid(String email) {
        try {
            DisifyResponseDTO response = webClient.get()
                    .uri("/email/{email}", email)
                    .retrieve()
                    .bodyToMono(DisifyResponseDTO.class)
                    .block();

            if (response == null) return false;


            return response.isFormat() && response.isDns() && !response.isDisposable();

        } catch (Exception e) {

            return true;
        }
    }
}