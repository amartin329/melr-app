package com.techelevator.model.SpoonacularModels.IngredientInformation;

import java.util.ArrayList;

public class IngredientInformation {
    public String aisle;
    public double amount;
    public ArrayList<String> categoryPath;
    public String consistency;
    public EstimatedCost estimatedCost;
    public int id;
    public String image;
    public ArrayList<Object> meta;
    public String name;
    public Nutrition nutrition;
    public String original;
    public String originalName;
    public ArrayList<String> possibleUnits;
    public ArrayList<String> shoppingListUnits;
    public String unit;
    public String unitLong;
    public String unitShort;

    public IngredientInformation() {
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(ArrayList<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public EstimatedCost getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(EstimatedCost estimatedCost) {
        this.estimatedCost = estimatedCost;
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

    public ArrayList<Object> getMeta() {
        return meta;
    }

    public void setMeta(ArrayList<Object> meta) {
        this.meta = meta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public ArrayList<String> getPossibleUnits() {
        return possibleUnits;
    }

    public void setPossibleUnits(ArrayList<String> possibleUnits) {
        this.possibleUnits = possibleUnits;
    }

    public ArrayList<String> getShoppingListUnits() {
        return shoppingListUnits;
    }

    public void setShoppingListUnits(ArrayList<String> shoppingListUnits) {
        this.shoppingListUnits = shoppingListUnits;
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
