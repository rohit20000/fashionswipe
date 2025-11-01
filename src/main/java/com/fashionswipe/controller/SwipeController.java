package com.fashionswipe.controller;

import com.fashionswipe.model.Product;
import com.fashionswipe.model.Swipe;
import com.fashionswipe.model.User;
import com.fashionswipe.repository.ProductRepository;
import com.fashionswipe.repository.SwipeRepository;
import com.fashionswipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/swipes")
@CrossOrigin(origins = "*")
public class SwipeController {

    @Autowired
    private SwipeRepository swipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Swipe swipe(@RequestParam Long userId,
                       @RequestParam Long productId,
                       @RequestParam boolean liked) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Swipe swipe = new Swipe();
        swipe.setUser(user);
        swipe.setProduct(product);
        swipe.setLiked(liked);

        return swipeRepository.save(swipe);
    }

    @GetMapping("/user/{userId}")
    public List<Swipe> getUserSwipes(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return swipeRepository.findByUser(user);
    }
}
