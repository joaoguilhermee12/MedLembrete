package com.joaoguilhermee.MedLembrete;

import com.joaoguilhermee.MedLembrete.Service.External.EmailValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailValidationTest {

    @Autowired
    private EmailValidationService emailValidationService;

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