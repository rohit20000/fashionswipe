package com.fashionswipe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    @Column(nullable = false)
    private String category;

    private double price;
    private String imageUrl;

    @Column(length = 1000)
    private String description;

    private String source; // e.g. "Ajio", "Myntra", "Amazon", "Flipkart"
    
}
