package com.joaoguilhermee.MedLembrete;

import com.joaoguilhermee.MedLembrete.Model.Role;
import com.joaoguilhermee.MedLembrete.Model.User;
import com.joaoguilhermee.MedLembrete.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@medLembrete.com").isEmpty()) {
            User admin = new User();
            admin.setNome("Admin");
            admin.setEmail("admin@medLembrete.com");
            admin.setSenha("admin123");
            admin.setCPF("12345678901");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}