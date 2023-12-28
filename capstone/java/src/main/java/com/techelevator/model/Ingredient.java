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

    private double quantity;
    private int msmId;

    private String msmUnit;

    private int userId;





    public Ingredient() {}

    public Ingredient(int ingId, int ingTypeId, int nutritionId, String ingName, Nutrition nutrition, double quantity, int msmId) {
        this.ingId = ingId;
        this.ingTypeId = ingTypeId;
        this.nutritionId = nutritionId;
        this.ingName = ingName;
        this.nutrition = nutrition;
        this.quantity = quantity;
        this.msmId = msmId;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getMsmId() {
        return msmId;
    }

    public void setMsmId(int msmId) {
        this.msmId = msmId;
    }

    public String getMsmUnit() {
        return msmUnit;
    }

    public void setMsmUnit(String msmUnit) {
        this.msmUnit = msmUnit;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
