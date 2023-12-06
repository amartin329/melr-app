package com.techelevator.model.website;

import java.util.List;

public class Meal {

    private int meal_id;
    private String meal_name;
    private int meal_type_id;
    private List<Recipe> recipes;
    private List<Meal> meals;

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

    public List<Recipe> getRecipes(){
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes){
        this.recipes = recipes;
    }

    public List<Meal> getMeals() {
        return meals;
    }

}
