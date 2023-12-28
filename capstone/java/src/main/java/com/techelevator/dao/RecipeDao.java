package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;
import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {
    List<Recipe> listAllRecipes(int userId);
    Recipe getRecipeDetailsById(int recId, int userId);
    Recipe createRecipe(Recipe recipe);
    boolean updateRecipeInfo(Recipe recipe, int userId);
    int addIngredientToRecipe(int recipeId, int ingId, int msmId, double quantity, int userId);
    int removeIngredientFromRecipe(int recipeId, int ingId);
    List<Ingredient> getIngredientListForRecipe(int recId);
    Nutrition getNutritionForIngredient(int ingId);

    List<Nutrition> getNutritionForRecipe(int recId);

    // maybe not needed
    int deleteRecipe(int recipe_id);







}
