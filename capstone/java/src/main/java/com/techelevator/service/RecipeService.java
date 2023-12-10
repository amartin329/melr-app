package com.techelevator.service;

import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);
    Recipe createRecipe(Recipe recipe);
    Recipe updateRecipeInfo(int id, Recipe recipe);
    int addIngredientToRecipe(int recipeId, int ingId);
    int removeIngredientFromRecipe(int recipeId, int ingId);
}
