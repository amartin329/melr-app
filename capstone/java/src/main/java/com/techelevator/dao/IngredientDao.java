package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;

import com.techelevator.model.Recipe;
import java.util.List;

public interface IngredientDao {
    List<Ingredient> listAllIngredients();
    Ingredient getIngredientById(int ingId);
    Ingredient createIngredient(Ingredient ingredient);
    boolean updateIngredient(Ingredient ingredient);
    Nutrition getNutritionForIngredient(int ingId);

    // may not be needed
    int deleteIngredient(int ing_id);
  //Ingredient updateIngredient(Ingredient ingredient);



}
