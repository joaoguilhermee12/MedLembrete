package com.joaoguilhermee.MedLembrete.Service;

import com.joaoguilhermee.MedLembrete.Exception.ResourceNotFoundException;
import com.joaoguilhermee.MedLembrete.Model.Role;
import com.joaoguilhermee.MedLembrete.Model.User;
import com.joaoguilhermee.MedLembrete.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User searchUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
    }

    public User updateUser(Long userId, User newData) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));

        user.setNome(newData.getNome());
        user.setEmail(newData.getEmail());
        user.setSenha(newData.getSenha());
        user.setCpf(newData.getCpf());

        return userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    private void validateAdmin(Long adminId) {
        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", adminId));
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Acesso negado: apenas ADMIN pode realizar esta ação");
        }
    }

    public User disableUser(Long adminId, Long userId) {
        validateAdmin(adminId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        user.setAtivo(false);
        return userRepository.save(user);
    }

    public void deleteUser(Long adminId, Long userId) {
        validateAdmin(adminId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        userRepository.delete(user);
    }

    public User enableUser(Long adminId, Long userId) {
        validateAdmin(adminId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        user.setAtivo(true);
        return userRepository.save(user);
    }
}