package com.techelevator.service;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Mealplan;

import java.security.Principal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients(Principal user);
    Ingredient getIngredientById(int id, Principal user);
    Ingredient createIngredient(Ingredient ingredient, Principal user);
    Ingredient updateIngredient(int id, Ingredient updatedIngredient);
}
