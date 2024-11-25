package com.flavourbook.FlavourBook.services;

import com.flavourbook.FlavourBook.dto.ReceipeDTO;
import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.entity.ReceipeCategory;
import com.flavourbook.FlavourBook.repository.ReceipeCategoryRepository;
import com.flavourbook.FlavourBook.repository.ReceipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceipeService {

    @Autowired
    private ReceipeRepository receipeRepository;

    @Autowired
    private ReceipeCategoryRepository recipeCategoryRepository;

    public List<ReceipeDTO> getAllRecipes() {
        return receipeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReceipeDTO getReceipeById(Long id) {
        return receipeRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id));
    }

    public ReceipeDTO addRecipe(ReceipeDTO receipeDTO) {
        ReceipeCategory category = recipeCategoryRepository.findById(receipeDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Receipe receipe = new Receipe();
        receipe.setName(receipeDTO.getName());
        receipe.setDescription(receipeDTO.getDescription());
        receipe.setIngredients(receipeDTO.getIngredients());
        receipe.setInstructions(receipeDTO.getInstructions());
        receipe.setCategory(category);

        Receipe savedReceipe = receipeRepository.save(receipe);
        return toDTO(savedReceipe);
    }

    public List<ReceipeDTO> searchByName(String name) {
        return receipeRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReceipeDTO updateRecipe(Long id, ReceipeDTO receipeDTO) {
        Receipe receipe = receipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id));

        // Update fields
        receipe.setName(receipeDTO.getName());
        receipe.setDescription(receipeDTO.getDescription());
        receipe.setIngredients(receipeDTO.getIngredients());
        receipe.setInstructions(receipeDTO.getInstructions());

        // Update category if provided
        if (receipeDTO.getCategoryId() != null) {
            ReceipeCategory category = recipeCategoryRepository.findById(receipeDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            receipe.setCategory(category);
        }

        Receipe updatedReceipe = receipeRepository.save(receipe);
        return toDTO(updatedReceipe);
    }



    private ReceipeDTO toDTO(Receipe receipe) {
        ReceipeDTO dto = new ReceipeDTO();
        dto.setId(receipe.getId());
        dto.setName(receipe.getName());
        dto.setDescription(receipe.getDescription());
        dto.setIngredients(receipe.getIngredients());
        dto.setInstructions(receipe.getInstructions());
        dto.setCategoryId(receipe.getCategory().getId());
        return dto;
    }
}
