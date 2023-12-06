package com.techelevator.dao.website;

import com.techelevator.model.Mealplan;

import java.util.List;

public interface MealplanDao {

    Mealplan createMealplan(Mealplan mealplan);

    int deleteMealplan(int mealplan_id);

    List<Mealplan> listMealPlanByTypeId(int mealplan_type_id);

    List<Mealplan> listAllMealplans();

    Mealplan listMealplanById(int mealplan_id);
}
