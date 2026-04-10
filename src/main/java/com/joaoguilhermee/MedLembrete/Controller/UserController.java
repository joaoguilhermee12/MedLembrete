package com.joaoguilhermee.MedLembrete.Controller;

import com.joaoguilhermee.MedLembrete.Model.DTO.UserStatusDTO;
import com.joaoguilhermee.MedLembrete.Model.User;
import com.joaoguilhermee.MedLembrete.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST / CRIAR USUÁRIO
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
    // GET / LISTAR USUÁRIO PELO ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> searchUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.searchUser(userId));
    }
    // PUT / ATUALIZAR USUÁRIO
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId,
                                           @RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }
    // GET / LISTAR TODOS USUÁRIOS
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    // DELETE / EXCLUIR USUÁRIO
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@RequestParam Long adminId,
                                           @PathVariable Long userId) {
        userService.deleteUser(adminId, userId);
        return ResponseEntity.noContent().build();
    }
    // PATCH / DESATIVAR USUÁRIO
    @PatchMapping("/{userId}/disable")
    public ResponseEntity<UserStatusDTO> disableUser(@RequestParam Long adminId,
                                                     @PathVariable Long userId) {
        return ResponseEntity.ok(userService.disableUser(adminId, userId));
    }
    // PATCH / REATIVAR USUÁRIO
    @PatchMapping("/{userId}/enable")
    public ResponseEntity<UserStatusDTO> enableUser(@RequestParam Long adminId,
                                                    @PathVariable Long userId) {
        return ResponseEntity.ok(userService.enableUser(adminId, userId));
    }
}