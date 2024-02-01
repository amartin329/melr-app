package com.techelevator.service;

import com.techelevator.model.Mealplan;

import java.util.List;

public interface MealplanService {
    List<Mealplan> getMealplans() throws InterruptedException;
    Mealplan getMealplanById(int id) throws InterruptedException;
    Mealplan createMealplan(Mealplan newMealPlan);
    Mealplan updateMealplanInfo(int id, Mealplan updatedMealplan);
    int addMealToMealplan(int mealplanId, int mealId);
    int removeMealFromMealplan(int mealplanId, int mealId);

}
