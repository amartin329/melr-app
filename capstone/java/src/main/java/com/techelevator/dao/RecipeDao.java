package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;
import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {
    List<Recipe> listAllRecipes();
    Recipe getRecipeDetailsById(int recId);
    Recipe createRecipe(Recipe recipe);
    boolean updateRecipeInfo(Recipe recipe);
    int addIngredientToRecipe(int recipeId, int ingId, int msmId, double quantity);
    int removeIngredientFromRecipe(int recipeId, int ingId);
    List<Ingredient> getIngredientListForRecipe(int recId);
    Nutrition getNutritionForIngredient(int ingId);

    List<Nutrition> getNutritionForRecipe(int recId);

    // maybe not needed
    int deleteRecipe(int recipe_id);







}
