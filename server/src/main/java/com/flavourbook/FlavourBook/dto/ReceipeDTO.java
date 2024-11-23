package com.flavourbook.FlavourBook.dto;
import lombok.Data;

import java.util.List;

@Data
public class ReceipeDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> ingredients;
    private String instructions;
    private Long categoryId;
}
