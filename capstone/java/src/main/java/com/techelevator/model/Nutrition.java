package com.techelevator.model;

import java.util.Objects;

public class Nutrition {
    private int nutritionId;

    private double calories;
    private double protein;
    private double carb;

    private double fat;

    public Nutrition(int nutritionId, double calories, double protein, double carb, double fat) {
        this.nutritionId = nutritionId;
        this.calories = calories;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
    }

    public Nutrition (){}

    public int getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(int nutritionId) {
        this.nutritionId = nutritionId;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nutrition nutrition = (Nutrition) o;
        return nutritionId == nutrition.nutritionId && Double.compare(nutrition.calories, calories) == 0 && Double.compare(nutrition.protein, protein) == 0 && Double.compare(nutrition.carb, carb) == 0 && Double.compare(nutrition.fat, fat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nutritionId, calories, protein, carb, fat);
    }
}
