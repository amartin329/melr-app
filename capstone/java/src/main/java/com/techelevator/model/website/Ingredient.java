package com.techelevator.model.website;

public class Ingredient {

    private int ing_id;     //G - should these be Longs?

    private String ing_name;

    private int ing_type_id;

    private int nutrition_id;

    public int getIng_id() {
        return ing_id;
    }

    public void setIng_id(int ing_id) {
        this.ing_id = ing_id;
    }

    public String getIng_name() {
        return ing_name;
    }

    public void setIng_name(String ing_name) {
        this.ing_name = ing_name;
    }

    public int getIng_type_id() {
        return ing_type_id;
    }

    public void setIng_type_id(int ing_type_id) {
        this.ing_type_id = ing_type_id;
    }

    public int getNutrition_id() {
        return nutrition_id;
    }

    public void setNutrition_id(int nutrition_id) {
        this.nutrition_id = nutrition_id;
    }
}
