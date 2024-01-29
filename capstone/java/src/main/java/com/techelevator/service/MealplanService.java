package com.techelevator.service;

import com.techelevator.model.Mealplan;

import java.security.Principal;
import java.util.List;

public interface MealplanService {
    List<Mealplan> getMealplans(Principal user) throws InterruptedException;
    Mealplan getMealplanById(int id, Principal user) throws InterruptedException;
    Mealplan createMealplan(Mealplan newMealPlan, Principal user);
    Mealplan updateMealplanInfo(int id, Mealplan updatedMealplan, Principal user);
    int addMealToMealplan(int mealplanId, int mealId, Principal user);
    int removeMealFromMealplan(int mealplanId, int mealId, Principal user);

}
