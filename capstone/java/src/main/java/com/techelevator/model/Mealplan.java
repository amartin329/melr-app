package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class Mealplan {
    private int mealplanId;

    private int mealplanTypeId;

    private String mealplanName;

    private List<Meal> mealList;

    public Mealplan(){}

    public Mealplan(int mealplanId, int mealplanTypeId, String mealplanName, List<Meal> mealList) {
        this.mealplanId = mealplanId;
        this.mealplanTypeId = mealplanTypeId;
        this.mealplanName = mealplanName;
        this.mealList = mealList;
    }

    public Mealplan(int mealplanId, int mealplanTypeId, String mealplanName) {
        this.mealplanId = mealplanId;
        this.mealplanTypeId = mealplanTypeId;
        this.mealplanName = mealplanName;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public int getMealplanTypeId() {
        return mealplanTypeId;
    }

    public void setMealplanTypeId(int mealplanTypeId) {
        this.mealplanTypeId = mealplanTypeId;
    }

    public String getMealplanName() {
        return mealplanName;
    }

    public void setMealplanName(String mealplanName) {
        this.mealplanName = mealplanName;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mealplan mealplan = (Mealplan) o;
        return mealplanId == mealplan.mealplanId && mealplanTypeId == mealplan.mealplanTypeId && Objects.equals(mealplanName, mealplan.mealplanName) && Objects.equals(mealList, mealplan.mealList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealplanId, mealplanTypeId, mealplanName, mealList);
    }
}
