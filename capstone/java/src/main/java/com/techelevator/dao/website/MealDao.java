package com.techelevator.dao.website;
import com.techelevator.model.website.Meal;

public interface MealDao {

    Meal createMeal(Meal meal);

    boolean deleteMeal(int meal_id);
}
