package com.fashionswipe.controller;

import com.fashionswipe.model.Product;
import com.fashionswipe.model.User;
import com.fashionswipe.repository.ProductRepository;
import com.fashionswipe.repository.SwipeRepository;
import com.fashionswipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SwipeRepository swipeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<Product> getRecommendations(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 1️⃣ Get all product IDs user has swiped
        List<Long> swipedProductIds = swipeRepository.findProductIdsSwipedByUser(userId);

        // 2️⃣ Get all products user hasn't swiped yet
        if (swipedProductIds.isEmpty()) {
            return productRepository.findAll(); // show all initially
        } else {
            return productRepository.findAll()
                    .stream()
                    .filter(p -> !swipedProductIds.contains(p.getId()))
                    .toList();
        }
    }

    @GetMapping("/{userId}/next")
public Product getNextProduct(@PathVariable Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Long> swipedIds = swipeRepository.findProductIdsSwipedByUser(userId);
    List<Product> products = productRepository.findAll();

    // Filter out already swiped products
    List<Product> unSwiped = products.stream()
            .filter(p -> !swipedIds.contains(p.getId()))
            .toList();

    if (unSwiped.isEmpty()) {
        throw new RuntimeException("No more products to recommend!");
    }

    // For now, just return the first one (we’ll randomize later)
    return unSwiped.get(0);
} 

@GetMapping("/{userId}/list")
public List<Product> getUnswipedProducts(@PathVariable Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Long> swipedIds = swipeRepository.findProductIdsSwipedByUser(userId);
    List<Product> products = productRepository.findAll();

    // Filter products user hasn’t swiped yet
    return products.stream()
            .filter(p -> !swipedIds.contains(p.getId()))
            .toList();
}


}
