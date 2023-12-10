package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingredient {
    private int ingId;
    private int ingTypeId;
    private int nutritionId;
    private String ingName;

    private Nutrition nutrition;



    public Ingredient() {}

    public Ingredient(int ingId, int ingTypeId, int nutritionId, String ingName, Nutrition nutrition) {
        this.ingId = ingId;
        this.ingTypeId = ingTypeId;
        this.nutritionId = nutritionId;
        this.ingName = ingName;
        this.nutrition = nutrition;
    }

    public int getIngId() {
        return ingId;
    }

    public void setIngId(int ingId) {
        this.ingId = ingId;
    }

    public int getIngTypeId() {
        return ingTypeId;
    }

    public void setIngTypeId(int ingTypeId) {
        this.ingTypeId = ingTypeId;
    }

    public int getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(int nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingId == that.ingId && ingTypeId == that.ingTypeId && nutritionId == that.nutritionId && Objects.equals(ingName, that.ingName) && Objects.equals(nutrition, that.nutrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingId, ingTypeId, nutritionId, ingName, nutrition);
    }
}
