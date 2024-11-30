package com.flavourbook.FlavourBook.services;

import com.flavourbook.FlavourBook.dto.ReceipeCategoryDTO;
import com.flavourbook.FlavourBook.dto.ReceipeDTO;
import com.flavourbook.FlavourBook.entity.ImageModel;
import com.flavourbook.FlavourBook.entity.ReceipeCategory;
import com.flavourbook.FlavourBook.repository.ReceipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReceipeCategoryService {

    @Autowired
    private ReceipeCategoryRepository recipeCategoryRepository;

    public List<ReceipeCategoryDTO> getAllCategories() {
        return recipeCategoryRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReceipeCategoryDTO getCategoryById(Long id) {
        return recipeCategoryRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Recipe category not found with ID: " + id));
    }

//    public ReceipeCategoryDTO addCategory(ReceipeCategoryDTO categoryDTO) {
//        ReceipeCategory category = new ReceipeCategory();
//        category.setName(categoryDTO.getName());
//        category.setDescription(categoryDTO.getDescription());
//        return toDTO(recipeCategoryRepository.save(category));
//    }

    public ReceipeCategoryDTO addCategoryWithImages(ReceipeCategoryDTO categoryDTO, Set<ImageModel> images) {
        ReceipeCategory category = new ReceipeCategory();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImages(images);

        ReceipeCategory savedCategory = recipeCategoryRepository.save(category);
        return toDTO(savedCategory);
    }

    public List<ReceipeCategoryDTO> searchByName(String name) {
        return recipeCategoryRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ReceipeCategoryDTO toDTO(ReceipeCategory category) {
        ReceipeCategoryDTO dto = new ReceipeCategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
