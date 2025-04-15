package com.example.recipe_api.dto;

import com.example.recipe_api.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseDTO {
    private String recipeId;
    private String name;
    private List<String> ingredients;
    private String instructions;
    private LocalDateTime createdAt;

    public RecipeResponseDTO toResponseDTO(Recipe recipe) {
        RecipeResponseDTO dto = new RecipeResponseDTO();
        dto.setRecipeId(recipe.getRecipeId());
        dto.setName(recipe.getName());
        dto.setIngredients(recipe.getIngredients());
        dto.setInstructions(recipe.getInstructions());
        dto.setCreatedAt(recipe.getCreatedAt());
        return dto;
    }

}