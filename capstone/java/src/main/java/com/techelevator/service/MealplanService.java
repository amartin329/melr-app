package com.techelevator.service;

import com.techelevator.model.Mealplan;

import java.util.List;

public interface MealplanService {
    List<Mealplan> getMealplans() throws InterruptedException;
    Mealplan getMealplanById(int id) throws InterruptedException;
    Mealplan createMealplan(Mealplan newMealPlan);

}
