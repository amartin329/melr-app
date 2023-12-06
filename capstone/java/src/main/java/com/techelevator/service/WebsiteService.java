package com.techelevator.service;

import com.techelevator.model.*;

import java.util.List;


public interface WebsiteService {
    List<User> getUsers();
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);

    Recipe addRecipe(Recipe newRecipe);

    Recipe modifyRecipe(Recipe updateRecipe);

    Ingredient addIngredient(Ingredient newIng);

    List<Mealplan> getMealplans() throws InterruptedException;
    Mealplan getMealplan(int id) throws InterruptedException;

    Mealplan createMealplan(Mealplan newMealplan);
    Meal getMeal(int id) throws InterruptedException;
    Meal createMeal(Meal newMeal);

    Meal updateMeal(int id, Meal updatedMeal);

}
