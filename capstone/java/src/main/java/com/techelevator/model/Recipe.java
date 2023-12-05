package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private int recipeId;
    private String recipeType;
    private String recipeTag;
    private String recipeName;
    private String picturePath;
    private int prepTime;
    private String instruction;
    private boolean isFavorited;

    private List<Ingredient> ingredientList;



    public Recipe(){}

    public Recipe(int recipeId, String recipeType, String recipeTag, String recipeName, String picturePath,
                  int prepTime, String instruction, boolean isFavorited, List<Ingredient> ingredientList) {
        this.recipeId = recipeId;
        this.recipeType = recipeType;
        this.recipeTag = recipeTag;
        this.recipeName = recipeName;
        this.picturePath = picturePath;
        this.prepTime = prepTime;
        this.instruction = instruction;
        this.isFavorited = isFavorited;
        this.ingredientList = ingredientList;
    }

    public Recipe(int recipeId, String recipeType, String recipeTag, String recipeName, String picturePath, int prepTime, String instruction, boolean isFavorited) {
        this.recipeId = recipeId;
        this.recipeType = recipeType;
        this.recipeTag = recipeTag;
        this.recipeName = recipeName;
        this.picturePath = picturePath;
        this.prepTime = prepTime;
        this.instruction = instruction;
        this.isFavorited = isFavorited;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getRecipeTag() {
        return recipeTag;
    }

    public void setRecipeTag(String recipeTag) {
        this.recipeTag = recipeTag;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && prepTime == recipe.prepTime && isFavorited == recipe.isFavorited && Objects.equals(recipeType, recipe.recipeType) && Objects.equals(recipeTag, recipe.recipeTag) && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(picturePath, recipe.picturePath) && Objects.equals(instruction, recipe.instruction) && Objects.equals(ingredientList, recipe.ingredientList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeType, recipeTag, recipeName, picturePath, prepTime, instruction, isFavorited, ingredientList);
    }
}
