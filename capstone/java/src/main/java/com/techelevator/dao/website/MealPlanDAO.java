package com.techelevator.dao.website;

import com.techelevator.model.website.MealPlan;

import java.util.List;

public interface MealPlanDAO {

    MealPlan createMealPlan(MealPlan mealPlan);

    int deleteMealPlan(int mealplan_id);

    List<MealPlan> listMealPlanByTypeId(int mealplan_type_id);

    List<MealPlan> listAllMealPlans();

    MealPlan listMealPlanById(int mealplan_id);
}
