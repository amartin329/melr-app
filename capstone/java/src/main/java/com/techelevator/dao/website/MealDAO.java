package com.techelevator.dao.website;
import com.techelevator.model.website.Meal;

public interface MealDAO {

    Meal createMeal(Meal meal);

    boolean deleteMeal(int meal_id);
}
