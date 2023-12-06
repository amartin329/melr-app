package com.techelevator.dao.website;

import java.util.List;

public interface MealDao {

    Meal createMeal(Meal meal);

    int deleteMeal(int meal_id);

    Meal listMealById(int meal_id);

    List<Meal> listMealByTypeId(int meal_type_id);

    List<Meal> listAllMeals();
}
