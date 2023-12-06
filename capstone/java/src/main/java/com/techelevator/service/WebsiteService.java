package com.techelevator.service;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Meal;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import com.techelevator.model.website.MealPlan;

import java.util.List;


public interface WebsiteService {
    List<User> getUsers();
    List<Recipe> getRecipes();
    Recipe getRecipeById(int id);

    Recipe addRecipe(Recipe newRecipe);

    Recipe modifyRecipe(Recipe updateRecipe);

    Ingredient addIngredient(Ingredient newIng);

    List<MealPlan> getMealPlans() throws InterruptedException;
    MealPlan getMealPlan(int id) throws InterruptedException;

    MealPlan createMealPlan(MealPlan newMealPlan);
    Meal getMeal(int id) throws InterruptedException;
    Meal createMeal(Meal newMeal);

    Meal updateMeal(int id, Meal updatedMeal);

}
