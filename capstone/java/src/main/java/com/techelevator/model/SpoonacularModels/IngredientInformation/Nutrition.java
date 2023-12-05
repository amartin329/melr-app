package com.techelevator.model.SpoonacularModels.IngredientInformation;

import java.util.ArrayList;

public class Nutrition {
    public CaloricBreakdown caloricBreakdown;
    public ArrayList<Flavonoid> flavonoids;
    public ArrayList<Nutrient> nutrients;
    public ArrayList<Property> properties;
    public WeightPerServing weightPerServing;

    public Nutrition() {
    }

    public CaloricBreakdown getCaloricBreakdown() {
        return caloricBreakdown;
    }

    public void setCaloricBreakdown(CaloricBreakdown caloricBreakdown) {
        this.caloricBreakdown = caloricBreakdown;
    }

    public ArrayList<Flavonoid> getFlavonoids() {
        return flavonoids;
    }

    public void setFlavonoids(ArrayList<Flavonoid> flavonoids) {
        this.flavonoids = flavonoids;
    }

    public ArrayList<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(ArrayList<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public WeightPerServing getWeightPerServing() {
        return weightPerServing;
    }

    public void setWeightPerServing(WeightPerServing weightPerServing) {
        this.weightPerServing = weightPerServing;
    }
}
