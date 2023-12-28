package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private int recipeId;
    private int recipeTypeId;
    private int recipeTagId;
    private String recipeName;
    private String picturePath;
    private int prepTime;
    private String instruction;
    private boolean isFavorited;

    private List<Ingredient> ingredientList;

    private int userId;



    public Recipe(){}

    public Recipe(int recipeId, int recipeTypeId, int recipeTagId, String recipeName, String picturePath, int prepTime,
                  String instruction, boolean isFavorited, List<Ingredient> ingredientList) {
        this.recipeId = recipeId;
        this.recipeTypeId = recipeTypeId;
        this.recipeTagId = recipeTagId;
        this.recipeName = recipeName;
        this.picturePath = picturePath;
        this.prepTime = prepTime;
        this.instruction = instruction;
        this.isFavorited = isFavorited;
        this.ingredientList = ingredientList;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeTypeId() {
        return recipeTypeId;
    }

    public void setRecipeTypeId(int recipeTypeId) {
        this.recipeTypeId = recipeTypeId;
    }

    public int getRecipeTagId() {
        return recipeTagId;
    }

    public void setRecipeTagId(int recipeTagId) {
        this.recipeTagId = recipeTagId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && recipeTypeId == recipe.recipeTypeId && recipeTagId == recipe.recipeTagId && prepTime == recipe.prepTime && isFavorited == recipe.isFavorited && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(picturePath, recipe.picturePath) && Objects.equals(instruction, recipe.instruction) && Objects.equals(ingredientList, recipe.ingredientList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeTypeId, recipeTagId, recipeName, picturePath, prepTime, instruction, isFavorited, ingredientList);
    }
}
