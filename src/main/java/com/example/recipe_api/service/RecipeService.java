package com.example.recipe_api.service;

import com.example.recipe_api.dto.RecipeResponseDTO;
import com.example.recipe_api.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    Recipe createRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Optional<RecipeResponseDTO> findByRecipeId(String recipeId);
//
//    Optional<Recipe> updateRecipe(String id, Recipe updatedRecipe);
}
