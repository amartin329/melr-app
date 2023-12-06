package com.techelevator.dao.website;

import java.util.List;

public interface MealPlanDao {

    MealPlan createMealPlan(MealPlan mealPlan);

    int deleteMealPlan(int mealplan_id);

    List<MealPlan> listMealPlanByTypeId(int mealplan_type_id);

    List<MealPlan> listAllMealPlans();

    MealPlan listMealPlanById(int mealplan_id);
}
