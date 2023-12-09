package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;

import com.techelevator.model.Recipe;
import java.util.List;

public interface IngredientDao {
    Ingredient createIngredient(Ingredient ingredient);

    //Ingredient updateIngredient(Ingredient ingredient);



    int deleteIngredient(int ing_id);

    //this method just lists all the ingredients that the logged-in user has in library
    List<Ingredient> listAllIngredients();

    // this method will list the details of a particular ingredient by its id
    Ingredient getIngredientById(int ingId);

    Nutrition getNutritionForIngredient(int ingId);
}
