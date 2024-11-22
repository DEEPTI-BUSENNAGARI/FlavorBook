package com.flavourbook.FlavourBook.services;

import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.repository.ReceipeRepository;
import entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ReceipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceipeService {
    private final ReceipeRepository receipeRepository;

    public List<Receipe> searchRecipes(String query) {
        return receipeRepository.findByTitleContainingIgnoreCase(query);
    }

    public List<Receipe> getPopularRecipes() {
        // Assuming recipes with the most comments are popular
        return receipeRepository.findPopularRecipes();
    }

    public List<Receipe> filterRecipesByPrepTime(int time) {
        return receipeRepository.findByPrepTimeLessThanEqual(time);
    }
}
