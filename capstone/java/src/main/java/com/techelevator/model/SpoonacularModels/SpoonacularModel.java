package com.techelevator.model.SpoonacularModels;

import java.util.ArrayList;

public class SpoonacularModel {

    public int number;
    public int offset;
    public ArrayList<Result> results;

    public int totalResults;

    public SpoonacularModel(int number, int offset, ArrayList<Result> results, int totalResults) {
        this.number = number;
        this.offset = offset;
        this.results = results;
        this.totalResults = totalResults;
    }

    public SpoonacularModel(){

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
