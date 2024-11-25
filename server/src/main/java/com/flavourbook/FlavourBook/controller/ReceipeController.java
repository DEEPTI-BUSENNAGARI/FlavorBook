package com.flavourbook.FlavourBook.controller;


import com.flavourbook.FlavourBook.dto.ReceipeDTO;
import com.flavourbook.FlavourBook.services.ReceipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/recipes")
public class ReceipeController {

    @Autowired
    private ReceipeService receipeService;

    @GetMapping
    public List<ReceipeDTO> getAllRecipes() {
        return receipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ReceipeDTO getRecipeById(@PathVariable Long id) {
        return receipeService.getReceipeById(id);
    }

    @PostMapping
    public ResponseEntity<ReceipeDTO> addRecipe(@RequestBody ReceipeDTO receipeDTO) {
        ReceipeDTO savedReceipe = receipeService.addRecipe(receipeDTO);
        return ResponseEntity.ok(savedReceipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceipeDTO> updateRecipe(@PathVariable Long id, @RequestBody ReceipeDTO receipeDTO) {
        ReceipeDTO updatedReceipe = receipeService.updateRecipe(id, receipeDTO);
        return ResponseEntity.ok(updatedReceipe);
    }


    @GetMapping("/search/name")
    public ResponseEntity<List<ReceipeDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(receipeService.searchByName(name));
    }

}
