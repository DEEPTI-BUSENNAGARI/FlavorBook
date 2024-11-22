package com.flavourbook.FlavourBook.controller;


import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.services.ReceipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class ReceipeController {
    private final ReceipeService receipeService;


    @GetMapping("/search")
    public ResponseEntity<List<Receipe>> searchRecipes(@RequestParam String q) {
        return ResponseEntity.ok(receipeService.searchRecipes(q));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Receipe>> getPopularRecipes() {
        return ResponseEntity.ok(receipeService.getPopularRecipes());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Receipe>> filterRecipesByTime(@RequestParam int time) {
        return ResponseEntity.ok(receipeService.filterRecipesByPrepTime(time));
    }
}
