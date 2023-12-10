package com.techelevator.service;

import com.techelevator.model.Meal;

import java.util.List;

public interface MealService {
    List<Meal> getMeals() throws InterruptedException;
    Meal getMealById(int id) throws InterruptedException;
    Meal createMeal(Meal meal);
    int addRecipeToMeal (int mealId, int recipeId);
    int removeRecipeFromMeal(int mealId, int recipeId);

}
