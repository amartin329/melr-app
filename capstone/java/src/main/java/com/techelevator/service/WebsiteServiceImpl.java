package com.techelevator.service;

import com.techelevator.dao.website.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebsiteServiceImpl implements WebsiteService {

    private WebsiteDao websiteDao;
    private MealDao mealDao;
    private UserDao userDao;
    private MealplanDao mealPlanDao;
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;



    public WebsiteServiceImpl(UserDao userDao, IngredientDao ingredientDao, RecipeDao recipeDao,
                              WebsiteDao websiteDao,  MealDao mealDao, MealplanDao mealPlanDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.mealDao = mealDao;
        this.mealPlanDao = mealPlanDao;
        this.websiteDao = websiteDao;
    }

    public List<User> getUsers() {
        try{
            List<User> users = userDao.getUsers();
            if (users == null) {
                throw new ServiceException("User list not found.");
            } else {
                return users;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public List<Recipe> getRecipes() {
        List<Recipe> allRecipes = new ArrayList<>();
        try{
            allRecipes = recipeDao.listAllRecipes(); // wait to see the corresponding method in recipe Dao
            if (allRecipes == null) {
                throw new ServiceException("Recipes not found.");
            } else {
                return allRecipes;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Recipe getRecipeById(int id) {
            try{
                Recipe recipe = recipeDao.getRecipeDetailsById(id); // wait to see the corresponding method in recipeDao
                if (recipe == null) {
                    throw new ServiceException("Recipe id: " + id + " was not found.");
                } else {
                    return recipe;
                }
            }catch(DaoException e){
                throw new ServiceException("An error has occurred: " + e.getMessage());
            }
        }

    public Recipe addRecipe(Recipe newRecipe){
        Recipe addedRecipe = new Recipe();
        try {
            addedRecipe.setRecipeType(newRecipe.getRecipeType());
            addedRecipe.setRecipeTag(newRecipe.getRecipeTag());
            addedRecipe.setRecipeName(newRecipe.getRecipeName());
            addedRecipe.setPicturePath(newRecipe.getPicturePath());
            addedRecipe.setPrepTime(newRecipe.getPrepTime());
            addedRecipe.setInstruction(newRecipe.getInstruction());
            addedRecipe.setFavorited(newRecipe.isFavorited());
            Recipe recipeToAdd = recipeDao.addRecipe(addedRecipe); // wait to see the corresponding method in the recipeDao
            return recipeToAdd;
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }

    }

    public Recipe modifyRecipe(Recipe updateRecipe){
        Recipe recipe;
        int recipeId;
        try {
            recipeId = updateRecipe.getRecipeId();
            recipe = recipeDao.getRecipeDetailsById(recipeId);
                Recipe recipeToUpdate = recipeDao.updateRecipe(recipe); // wait to see the corresponding method in the recipeDao
                return recipeToUpdate;
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Ingredient addIngredient(Ingredient newIng){
        Ingredient addedIngredient = new Ingredient();
        try {
            addedIngredient.setIngType(newIng.getIngType());
            addedIngredient.setIngName(newIng.getIngName());
            addedIngredient.setNutritionId(newIng.getNutritionId());

            Ingredient ingredientToAdd = ingredientDao.addIngredient(addedIngredient); // wait to see the corresponding method in the ingredientDao
            return ingredientToAdd;
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


    public List<Mealplan> getMealPlans() throws InterruptedException {
        Thread.sleep(2000); //Simulated loading time

        return mealPlanDao.getAllMealplans(); // wait to see the corresponding method in mealPlanDao
    }


    public Mealplan getMealPlan(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time

        Mealplan result = mealPlanDao.getMealplan(id); // wait to see the corresponding method in mealPlanDao
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    public Meal getMeal(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time

        Meal result = mealDao.getMeal(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No meal with that id.");
        } else {
            return result;
        }
    }


    public Mealplan createMealPlan(Mealplan newMealPlan) {
        try {
            return mealPlanDao.createMealplan(newMealPlan);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }



    public Meal createMeal(Meal newMeal) {
        Meal myMeal = new Meal();
        try {
            myMeal = mealDao.createMeal(newMeal.getMealplanId(), newMeal); // wait to see what's in there in mealDao
            return myMeal;
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


    public Meal updateMeal(int id, Meal updatedMeal) {
        updatedMeal.setMealId(id);
        if (mealDao.updateMeal(updatedMeal)) {
            return updatedMeal;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found to update.");
        }
    }

    public Mealplan updateMealPlan(int id, Mealplan updatedMealPlan) {
        updatedMealPlan.setMealplanId(id);
        if (mealPlanDao.updateMealplan(updatedMealPlan)) {
            return updatedMealPlan;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mealplan not found to update.");
        }
    }







}

