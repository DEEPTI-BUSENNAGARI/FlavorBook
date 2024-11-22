package com.flavourbook.FlavourBook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceipeCategoryDTO {

    private Long id;
    private String name;
    private String description;
}
