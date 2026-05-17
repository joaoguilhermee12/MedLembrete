package com.joaoguilhermee.MedLembrete;

import com.joaoguilhermee.MedLembrete.Service.External.EmailValidationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidationTest {

    private final EmailValidationService emailValidationService = new EmailValidationService();

    @Test
    void deveValidarEmailReal() {
        assertTrue(emailValidationService.isEmailValid("contato@google.com"));
    }

    @Test
    void deveRejeitarEmailIncompleto() {
        assertFalse(emailValidationService.isEmailValid("joao@"));
    }

    @Test
    void deveRejeitarEmailSemFormato() {
        assertFalse(emailValidationService.isEmailValid("email-invalido"));
    }
}