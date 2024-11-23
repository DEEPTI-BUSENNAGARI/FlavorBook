package com.flavourbook.FlavourBook.dto;

import lombok.Data;

@Data
public class FavoriteDTO {
    private Long id;
    private Long userId;
    private Long recipeId;
    private String receipeName;
    private String recipeDescription;



    public String getReceipeName() {
        return receipeName;
    }

    public void setReceipeName(String receipeName) {
        this.receipeName = receipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }
}
