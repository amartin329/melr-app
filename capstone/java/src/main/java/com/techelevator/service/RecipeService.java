package com.techelevator.service;

import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);
    Recipe createRecipe(Recipe recipe);
}
