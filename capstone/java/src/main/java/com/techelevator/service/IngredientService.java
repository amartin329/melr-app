package com.techelevator.service;

import com.techelevator.model.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients();
    Ingredient getIngredientById(int id);

}
