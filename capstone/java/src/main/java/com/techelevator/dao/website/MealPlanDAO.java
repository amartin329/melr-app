package com.techelevator.dao.website;

import com.techelevator.model.website.MealPlan;

public interface MealPlanDAO {
    //we need a createMeal() method
    MealPlan createMealPlan(MealPlan mealPlan);

    boolean deleteMealPlan(int mealplan_id);
}
