package com.techelevator.service;

import com.techelevator.model.Meal;

import java.security.Principal;
import java.util.List;

public interface MealService {
    List<Meal> getMeals(Principal user) throws InterruptedException;
    Meal getMealById(int id, Principal user) throws InterruptedException;
    Meal createMeal(Meal meal, Principal user);
    Meal updateMealInfo(int id, Meal updatedMeal, Principal user);
    int addRecipeToMeal (int mealId, int recipeId, Principal user);
    int removeRecipeFromMeal(int mealId, int recipeId, Principal user);

}
