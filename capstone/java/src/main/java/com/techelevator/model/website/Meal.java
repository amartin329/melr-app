package com.techelevator.model.website;

public class Meal {

    private int meal_id;
    private String meal_name;
    private int meal_type_id;

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public int getMeal_type_id() {
        return meal_type_id;
    }

    public void setMeal_type_id(int meal_type_id) {
        this.meal_type_id = meal_type_id;
    }
}