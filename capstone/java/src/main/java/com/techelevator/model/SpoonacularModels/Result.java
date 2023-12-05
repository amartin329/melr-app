package com.techelevator.model.SpoonacularModels;

public class Result {
    public int id;
    public String image;
    public String name;

    public Result(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Result(){

    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }


}
