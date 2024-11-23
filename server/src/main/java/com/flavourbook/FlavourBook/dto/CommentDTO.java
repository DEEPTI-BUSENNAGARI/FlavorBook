package com.flavourbook.FlavourBook.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long recipeId;
    private Long userId;
    private int rating;
    private String comment;
}
