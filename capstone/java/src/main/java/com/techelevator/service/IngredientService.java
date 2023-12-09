package com.techelevator.service;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Mealplan;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients();
    Ingredient getIngredientById(int id);
    Ingredient createIngredient(Ingredient ingredient);
}
