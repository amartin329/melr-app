package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class Meal {
    private int mealId;
    private int mealTypeId;
    private String mealName;

    private List<Recipe> recipeList;



    public Meal() {}

    public Meal(int mealId, int mealTypeId, String mealName, List<Recipe> recipeList) {
        this.mealId = mealId;
        this.mealTypeId = mealTypeId;
        this.mealName = mealName;
        this.recipeList = recipeList;
    }

    public Meal(int mealId, int mealTypeId, String mealName) {
        this.mealId = mealId;
        this.mealTypeId = mealTypeId;
        this.mealName = mealName;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(int mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return mealId == meal.mealId && mealTypeId == meal.mealTypeId && Objects.equals(mealName, meal.mealName) && Objects.equals(recipeList, meal.recipeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, mealTypeId, mealName, recipeList);
    }
}
