package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingredient {
    private int ingId;
    private String ingType;
    private int nutritionId;
    private String ingName;

    private Nutrition nutrition;



    public Ingredient() {}

    public Ingredient(int ingId, String ingType, int nutritionId, String ingName, Nutrition nutrition) {
        this.ingId = ingId;
        this.ingType = ingType;
        this.nutritionId = nutritionId;
        this.ingName = ingName;
        this.nutrition = nutrition;
    }

    public Ingredient(int ingId, String ingType, int nutritionId, String ingName) {
        this.ingId = ingId;
        this.ingType = ingType;
        this.nutritionId = nutritionId;
        this.ingName = ingName;
    }

    public int getIngId() {
        return ingId;
    }

    public void setIngId(int ingId) {
        this.ingId = ingId;
    }

    public String getIngType() {
        return ingType;
    }

    public void setIngType(String ingType) {
        this.ingType = ingType;
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
        return ingId == that.ingId && ingType == that.ingType && nutritionId == that.nutritionId && ingName.equals(that.ingName) && nutrition.equals(that.nutrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingId, ingType, nutritionId, ingName, nutrition);
    }
}
