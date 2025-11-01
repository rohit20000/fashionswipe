package com.fashionswipe.controller;

import com.fashionswipe.model.User;
import com.fashionswipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

// 🟢 Register
@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User user) {

    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Email already registered!"));
    }

    User savedUser = userRepository.save(user);
    return ResponseEntity.ok(savedUser);
}


    @ExceptionHandler(RuntimeException.class)
public ResponseEntity<Map<String, String>> handleException(RuntimeException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("message", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}
    // 🔵 Login
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return existingUser;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
