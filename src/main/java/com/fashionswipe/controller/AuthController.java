package com.fashionswipe.controller;

import com.fashionswipe.dto.LoginRequest;
import com.fashionswipe.dto.RegisterRequest;
import com.fashionswipe.model.User;
import com.fashionswipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest req) {

        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest req) {

        User user = userRepository.findByEmail(req.getEmail()).orElse(null);

        if (user == null || !user.getPassword().equals(req.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        return ResponseEntity.ok(user);
    }
}
