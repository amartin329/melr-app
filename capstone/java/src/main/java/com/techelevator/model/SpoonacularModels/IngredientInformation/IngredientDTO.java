package com.techelevator.model.SpoonacularModels.IngredientInformation;

import java.util.ArrayList;

public class IngredientDTO {

    private double amount;
    private int id;
    private String image;
    private String name;
    private NutritionDTO nutritionDTO;
    private ArrayList<String> possibleUnits;
    private String unit;
    private String unitLong;
    private String unitShort;

    public IngredientDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NutritionDTO getNutritionDTO() {
        return nutritionDTO;
    }

    public void setNutritionDTO(NutritionDTO nutritionDTO) {
        this.nutritionDTO = nutritionDTO;
    }

    public ArrayList<String> getPossibleUnits() {
        return possibleUnits;
    }

    public void setPossibleUnits(ArrayList<String> possibleUnits) {
        this.possibleUnits = possibleUnits;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }
}
