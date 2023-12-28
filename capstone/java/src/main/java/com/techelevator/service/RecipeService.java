package com.techelevator.service;

import com.techelevator.model.Recipe;

import java.security.Principal;
import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes(Principal user);
    Recipe getRecipeById(int id, Principal user);
    Recipe createRecipe(Recipe recipe, Principal user);
    Recipe updateRecipeInfo(int id, Recipe recipe, Principal user);
    int addIngredientToRecipe(int recipeId, int ingId, int msmId, double quantity, Principal user);
    int removeIngredientFromRecipe(int recipeId, int ingId, Principal user);
}
