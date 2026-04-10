package com.joaoguilhermee.MedLembrete;

import com.joaoguilhermee.MedLembrete.Model.User;
import com.joaoguilhermee.MedLembrete.Repository.MedicineRepository;
import com.joaoguilhermee.MedLembrete.Repository.UserRepository;
import com.joaoguilhermee.MedLembrete.Service.MedicineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import com.joaoguilhermee.MedLembrete.Model.Medicine;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MedLembreteApplicationTests {

	@Autowired
	private MedicineService medicineService;
	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private UserRepository userRepository;

	// Verifica se o contexto da aplicação sobe corretamente
	@Test
	void contextLoads() {
	}

	// TESTE 1 - CAMINHO FELIZ
	// Verifica se um usuário com dados válidos é criado e salvo corretamente no banco
	@Test
	void shouldCreateUserSuccessfully() {
		User user = new User();
		user.setNome("João Silva");
		user.setEmail("joao@email.com");
		user.setSenha("123456");
		user.setCpf("98765432100");

		User saved = userRepository.save(user);

		assertNotNull(saved.getId());
		assertEquals("João Silva", saved.getNome());
		assertEquals("joao@email.com", saved.getEmail());
		System.out.println("Usuário criado com sucesso! ID gerado: " + saved.getId());
	}
	// TESTE 2 - ENTRADA INVÁLIDA
	// Verifica se o sistema rejeita um usuário com CPF duplicado
	@Test
	void shouldNotAllowDuplicateCPF() {
		User user1 = new User();
		user1.setNome("Usuário Um");
		user1.setEmail("user1@email.com");
		user1.setSenha("123456");
		user1.setCpf("11122233344");
		userRepository.save(user1);

		User user2 = new User();
		user2.setNome("Usuário Dois");
		user2.setEmail("user2@email.com");
		user2.setSenha("123456");
		user2.setCpf("11122233344");

		assertThrows(Exception.class, () -> {
			userRepository.saveAndFlush(user2);
		});
		System.out.println("Sistema bloqueou CPF duplicado corretamente!");
	}
}