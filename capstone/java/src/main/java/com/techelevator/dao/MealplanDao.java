package com.techelevator.dao;

import com.techelevator.model.Mealplan;

import java.util.List;

public interface MealplanDao {

    List<Mealplan> listAllMealplans(int userId);
    Mealplan listMealplanById(int mealplan_id, int userId);
    Mealplan createMealplan(Mealplan mealplan);
    boolean updateMealplanInfo(Mealplan mealplan, int userId);
    int addMealToMealplan(int mealplanId, int mealId);
    int removeMealFromMealplan(int mealplanId, int mealId);

    // these two may not be needed
    int deleteMealplan(int mealplan_id);
    List<Mealplan> listMealplanByTypeId(int mealplan_type_id);
}
