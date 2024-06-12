package org.projeto.data.controllers;

import org.projeto.data.entities.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.projeto.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        System.out.println(email);
        try {
            User user = UserService.login(email, password);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            UserService.register(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        try {
            User user = UserService.getById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
