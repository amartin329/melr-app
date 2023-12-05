package com.techelevator.model.SpoonacularModels.IngredientInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutritionDTO {

    private int nutritionId;
   private double calories;
   private double protein;
   private double fat;

    public NutritionDTO() {
    }

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

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void nutritionMap(ArrayList<Nutrient> nutrientList){
        Map<String, Double> nutritionMap = new HashMap<>();
        for(Nutrient nutrient:nutrientList){
            if(nutrient.getName().equals("calories")){
                setCalories(nutrient.getAmount());
            }else if(nutrient.getName().equals("fat")){
                setFat(nutrient.getAmount());
            }else if(nutrient.getName().equals("protein")){
                setProtein(nutrient.getAmount());
            }
        }

    }
}
