package com.techelevator.model.SpoonacularModels.IngredientInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutritionDTO {

    private int nutritionId;
   private double calories;
   private double protein;
   private double fat;

   private String type;

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

    public NutritionDTO nutritionMap(ArrayList<Nutrient> nutrientList){
        NutritionDTO nutritionDTO = new NutritionDTO();
        for(Nutrient nutrient:nutrientList){
            if(nutrient.getName().equals("Calories")){
                nutritionDTO.setCalories(nutrient.getAmount());
            }else if(nutrient.getName().equals("Cat")){
                nutritionDTO.setFat(nutrient.getAmount());
            }else if(nutrient.getName().equals("Protein")){
                nutritionDTO.setProtein(nutrient.getAmount());
            }
        }
        return nutritionDTO;

    }
}
