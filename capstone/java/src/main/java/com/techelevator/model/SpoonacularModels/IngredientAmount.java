package com.techelevator.model.SpoonacularModels;

public class IngredientAmount {
    public double amount;
    public String unit;

    public IngredientAmount() {
    }

    public double getAmount(){
        return this.amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getUnit(){
        return this.unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }
}
