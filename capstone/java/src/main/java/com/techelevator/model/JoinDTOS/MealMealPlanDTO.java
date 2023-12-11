package com.techelevator.model.JoinDTOS;

public class MealMealPlanDTO {
    private int mealplanId;
    private int mealId;

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public MealMealPlanDTO(int mealplanId, int mealId) {
        this.mealplanId = mealplanId;
        this.mealId = mealId;
    }
}