package com.techelevator.model.website;

public class Recipe {

    private int recipe_id;
    private int recipe_type_id;
    private int recipe_tag_id;
    private String recipe_name;
    private String picture_path;
    private int prep_time;
    private String instruction;
    private boolean favorited;

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getRecipe_type_id() {
        return recipe_type_id;
    }

    public void setRecipe_type_id(int recipe_type_id) {
        this.recipe_type_id = recipe_type_id;
    }

    public int getRecipe_tag_id() {
        return recipe_tag_id;
    }

    public void setRecipe_tag_id(int recipe_tag_id) {
        this.recipe_tag_id = recipe_tag_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(int prep_time) {
        this.prep_time = prep_time;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
