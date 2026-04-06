package com.joaoguilhermee.MedLembrete.Controller;

import com.joaoguilhermee.MedLembrete.Model.User;
import com.joaoguilhermee.MedLembrete.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /usuarios
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    // GET
    @GetMapping("/{userId}")
    public ResponseEntity<User> search(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.searchUser(userId));
    }

    // PUT
    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId,
                                          @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    // DESATIVAR
    @PatchMapping("/{userId}/desativar")
    public ResponseEntity<User> desativar(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.disableUser(userId));
    }
}