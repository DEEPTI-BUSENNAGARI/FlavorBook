package com.flavourbook.FlavourBook.controller;

import com.flavourbook.FlavourBook.dto.FavoriteDTO;
import com.flavourbook.FlavourBook.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")

public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // Add a new favorite
    @PostMapping("/{userId}/{recipeId}")
    public ResponseEntity<FavoriteDTO> addFavorite(@PathVariable Long userId, @PathVariable Long recipeId) {
        FavoriteDTO favoriteDTO = favoriteService.addFavorite(userId, recipeId);
        return new ResponseEntity<>(favoriteDTO, HttpStatus.CREATED);
    }

    // Get all favorites by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByUserId(@PathVariable Long userId) {
        List<FavoriteDTO> favorites = favoriteService.getFavoritesByUserId(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    // Remove a favorite
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long id) {
        favoriteService.removeFavorite(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Favorite with ID " + id + " deleted successfully");
    }
}

