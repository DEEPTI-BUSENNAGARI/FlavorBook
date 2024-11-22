package com.flavourbook.FlavourBook.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relationship with User

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Receipe recipe; // Relationship with Recipe
}
