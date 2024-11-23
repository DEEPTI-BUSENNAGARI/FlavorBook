package com.flavourbook.FlavourBook.controller;

import com.flavourbook.FlavourBook.dto.ReceipeCategoryDTO;
import com.flavourbook.FlavourBook.dto.ReceipeDTO;
import com.flavourbook.FlavourBook.entity.ReceipeCategory;
import com.flavourbook.FlavourBook.services.ReceipeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ReceipeCategoryController {

    @Autowired
    private ReceipeCategoryService receipeCategoryService;

    @GetMapping
    public ResponseEntity<List<ReceipeCategoryDTO>> getAllCategories() {
        List<ReceipeCategoryDTO> categories = receipeCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceipeCategoryDTO> getCategoryById(@PathVariable Long id) {
        ReceipeCategoryDTO categoryDTO = receipeCategoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<ReceipeCategoryDTO> addCategory(@RequestBody ReceipeCategoryDTO categoryDTO) {
        ReceipeCategoryDTO savedCategory = receipeCategoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ReceipeCategoryDTO>> searchByName(@RequestParam String name) {
        List<ReceipeCategoryDTO> categories = receipeCategoryService.searchByName(name);
        return ResponseEntity.ok(categories);
    }

}
