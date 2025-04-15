package com.example.recipe_api.service.impl;

import com.example.recipe_api.RecipeRepository.RecipeRepository;
import com.example.recipe_api.dto.RecipeResponseDTO;
import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Logger log = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        log.info("Creating recipe: {}", recipe);
        // Generate 16-character recipeId
        String recipeId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        recipe.setRecipeId(recipeId);
        recipe.setCreatedAt(LocalDateTime.now());
        Recipe saved = recipeRepository.save(recipe);
        log.info("Created recipe with ID: {}, recipeId: {}", saved.getId(), saved.getRecipeId());
        return saved;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        log.info("Fetching all recipes");
        List<Recipe> recipes = recipeRepository.findAll();
        log.info("Retrieved {} recipes", recipes.size());
        return  recipes;
    }

    @Override
    public Optional<RecipeResponseDTO> findByRecipeId(String recipeId) {
        log.info("Fetching recipe with recipeId: {}", recipeId);
        Optional<Recipe> recipe = recipeRepository.findByRecipeId(recipeId);
        if (recipe.isPresent()) {
            log.info("Found recipe with recipeId: {}", recipeId);
        } else {
            log.warn("Recipe with recipeId {} not found", recipeId);
        }
        return recipe.map(r -> new RecipeResponseDTO().toResponseDTO(r));
    }
}
