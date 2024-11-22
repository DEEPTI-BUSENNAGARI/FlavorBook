package com.flavourbook.FlavourBook.controller;

import com.flavourbook.FlavourBook.dto.ReceipeCategoryDTO;
import com.flavourbook.FlavourBook.entity.Category;
import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.services.ReceipeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class ReceipeCategoryController {
    private final ReceipeCategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody ReceipeCategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}/recipes")
    public ResponseEntity<List<Receipe>> getRecipesByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getRecipesByCategory(id));
    }
}
