package com.flavourbook.FlavourBook.services;

import com.flavourbook.FlavourBook.dto.FavoriteDTO;
import com.flavourbook.FlavourBook.entity.Favorite;
import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.entity.User;
import com.flavourbook.FlavourBook.repository.FavoriteRepository;
import com.flavourbook.FlavourBook.repository.ReceipeRepository;
import com.flavourbook.FlavourBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReceipeRepository receipeRepository;

    // Add a new favorite
    public FavoriteDTO addFavorite(Long userId, Long recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Receipe recipe = receipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setRecipe(recipe);

        Favorite savedFavorite = favoriteRepository.save(favorite);

        return toDTO(savedFavorite);
    }

    // Get all favorites by user ID
    public List<FavoriteDTO> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Remove a favorite
    public void removeFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }

    // Convert entity to DTO
    private FavoriteDTO toDTO(Favorite favorite) {
        FavoriteDTO dto = new FavoriteDTO();
        dto.setId(favorite.getId());
        dto.setUserId(favorite.getUser().getId());
        dto.setRecipeId(favorite.getRecipe().getId());
        dto.setReceipeName(favorite.getRecipe().getName());
        dto.setRecipeDescription(favorite.getRecipe().getDescription());
        return dto;
    }
}
