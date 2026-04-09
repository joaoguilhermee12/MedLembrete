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
    public User updateUser(Long userId, User dadosNovos) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));

        user.setNome(dadosNovos.getNome());
        user.setEmail(dadosNovos.getEmail());
        user.setSenha(dadosNovos.getSenha());
        user.setCPF(dadosNovos.getCPF());

        return userRepository.save(user);
    }
    public User disableUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));

        user.setAtivo(false);
        return userRepository.save(user);
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    private void validarAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Acesso negado: apenas ADMIN pode realizar esta ação");
        }
    }

    public User desativarUsuario(Long adminId, Long userId) {
        validarAdmin(adminId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        user.setAtivo(false);
        return userRepository.save(user);
    }

    public void deletarUsuario(Long adminId, Long userId) {
        validarAdmin(adminId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        userRepository.delete(user);
    }

    public User reativarUsuario(Long adminId, Long userId) {
        validarAdmin(adminId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));
        user.setAtivo(true);
        return userRepository.save(user);
    }
}