package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.Recipe;

import java.util.List;

public interface MealDao {

    List<Meal> listAllMeals(int userId);
    Meal listMealById(int meal_id, int userId);
    Meal createMeal(Meal meal);
    boolean updateMealInfo(Meal meal, int userId);
    int addRecipeToMeal(int mealId, int recipeId);
    int removeRecipeFromMeal(int mealId, int recipeId);
    List<Recipe> listRecipesByMealId(int recipe_id);


    // may need to clean up these two methods
    int deleteMeal(int meal_id);
    List<Meal> listMealByTypeId(int meal_type_id);







}
