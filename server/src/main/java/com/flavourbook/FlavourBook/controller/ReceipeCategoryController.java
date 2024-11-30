package com.flavourbook.FlavourBook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flavourbook.FlavourBook.dto.ReceipeCategoryDTO;
import com.flavourbook.FlavourBook.entity.ImageModel;
import com.flavourbook.FlavourBook.services.ReceipeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("http://localhost:4200")
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

//    @PostMapping
//    public ResponseEntity<ReceipeCategoryDTO> addCategory(@RequestBody ReceipeCategoryDTO categoryDTO) {
//        ReceipeCategoryDTO savedCategory = receipeCategoryService.addCategory(categoryDTO);
//        return ResponseEntity.ok(savedCategory);
//    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ReceipeCategoryDTO> addCategory(
            @RequestPart("category") String categoryJson,
            @RequestPart(value = "imageFiles", required = false) MultipartFile[] files) {
        try {
            // Deserialize the JSON string into a DTO
            ObjectMapper objectMapper = new ObjectMapper();
            ReceipeCategoryDTO categoryDTO = objectMapper.readValue(categoryJson, ReceipeCategoryDTO.class);

            // Process image files if present
            Set<ImageModel> images = (files != null) ? uploadImages(files) : new HashSet<>();

            // Call service to save category with images
            ReceipeCategoryDTO savedCategory = receipeCategoryService.addCategoryWithImages(categoryDTO, images);

            return ResponseEntity.ok(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Set<ImageModel> uploadImages(MultipartFile[] files) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : files) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ReceipeCategoryDTO>> searchByName(@RequestParam String name) {
        List<ReceipeCategoryDTO> categories = receipeCategoryService.searchByName(name);
        return ResponseEntity.ok(categories);
    }
}
