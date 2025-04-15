package com.example.recipe_api.RecipeRepository;

import com.example.recipe_api.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
//    @Query("{ 'ingredients': { $in: [?0] } }")
//    List<Recipe> findByIngredient(String ingredient);

    Optional<Recipe> findByRecipeId(String recipeId);

}