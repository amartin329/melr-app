package com.techelevator.model.website;

import java.util.List;

public class MealPlan {

    private int mealplan_id;
    private String mealplan_name;
    private int mealplan_type_id;
    private List<Meal> meals;

    public int getMealplan_id() {
        return mealplan_id;
    }

    public void setMealplan_id(int mealplan_id) {
        this.mealplan_id = mealplan_id;
    }

    public String getMealplan_name() {
        return mealplan_name;
    }

    public void setMealplan_name(String mealplan_name) {
        this.mealplan_name = mealplan_name;
    }

    public int getMealplan_type_id() {
        return mealplan_type_id;
    }

    public void setMealplan_type_id(int mealplan_type_id) {
        this.mealplan_type_id = mealplan_type_id;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
