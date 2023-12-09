package com.techelevator.dao;

import com.techelevator.model.Mealplan;

import java.util.List;

public interface MealplanDao {

    List<Mealplan> listAllMealplans();
    Mealplan listMealplanById(int mealplan_id);
    Mealplan createMealplan(Mealplan mealplan);
    Mealplan updateMealplanInfo(Mealplan mealplan);
    int addMealToMealplan(int mealplanId, int mealId);
    int removeMealFromMealplan(int mealplanId, int mealId);

    // these two may need to be cleaned up
    int deleteMealplan(int mealplan_id);
    List<Mealplan> listMealPlanByTypeId(int mealplan_type_id);
}
