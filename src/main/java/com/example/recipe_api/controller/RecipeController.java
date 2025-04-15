package com.example.recipe_api.controller;

import com.example.recipe_api.dto.RecipeResponseDTO;
import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRecipe(@RequestBody Recipe recipe) {
        log.info("POST /recipes - Creating recipe: {}", recipe);
        Recipe savedRecipe = recipeService.createRecipe(recipe);
        log.info("RecipeId: {}", savedRecipe.getId());
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("recipeId", savedRecipe.getRecipeId());
        response.put("createdAt", savedRecipe.getCreatedAt());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeResponseDTO> getRecipeByRecipeId(@PathVariable String recipeId) {
        return recipeService.findByRecipeId(recipeId)
                .map(recipe -> new ResponseEntity<>(recipe, HttpStatus.OK))
                .orElseGet(() -> {
                    log.warn("Recipe not found {}", recipeId);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
