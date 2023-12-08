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
    private MealplanDao mealplanDao;
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;



    public WebsiteServiceImpl(UserDao userDao, IngredientDao ingredientDao, RecipeDao recipeDao,
                              MealDao mealDao, MealplanDao mealplanDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.mealDao = mealDao;
        this.mealplanDao = mealplanDao;

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

    public List<Ingredient> getIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        try{
            allIngredients = ingredientDao.listAllIngredients(); // wait to see the corresponding method in recipe Dao
            if (allIngredients == null) {
                throw new ServiceException("Recipes not found.");
            } else {
                return allIngredients;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Ingredient getIngredientById(int id) {
        try{
            Ingredient ingredient = ingredientDao.getIngredientById(id); // wait to see the corresponding method in recipeDao
            if (ingredient == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return ingredient;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Ingredient addIngredient(Ingredient newIng){
        Ingredient addedIngredient = new Ingredient();
        try {
            addedIngredient.setIngType(newIng.getIngType());
            addedIngredient.setIngName(newIng.getIngName());
            addedIngredient.setNutritionId(newIng.getNutritionId());

            Ingredient ingredientToAdd = ingredientDao.createIngredient(addedIngredient);
            return ingredientToAdd;
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
            Recipe recipeToAdd = recipeDao.createRecipe(addedRecipe); // wait to see the corresponding method in the recipeDao
            return recipeToAdd;
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }

    }
    // TODO this method is just for the purpose of creating a modifyRecipe method which only updates the metadata of the Recipe
    // it doesn't do anything with updating the IngredientList of the data yet. This may call for a different methods with a different
    // ingredientDto to accomodate the requirements. I don't know how to do it yet!

    public Recipe modifyRecipeInfo(Recipe recipeToUpdate){
        Recipe recipe;
        int recipeId;
        try {
            recipeId = recipeToUpdate.getRecipeId();
            recipe = recipeDao.getRecipeDetailsById(recipeId);
                Recipe updatedRecipe = recipeDao.updateRecipeInfo(recipe); // wait to see the corresponding method in the recipeDao
                return updatedRecipe;
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }




    public List<Mealplan> getMealplans() throws InterruptedException {
        Thread.sleep(2000); //Simulated loading time

        return mealplanDao.listAllMealplans(); // wait to see the corresponding method in mealPlanDao
    }


    public Mealplan getMealplanById(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time

        Mealplan result = mealplanDao.listMealplanById(id); // wait to see the corresponding method in mealPlanDao
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    public Meal getMeal(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time

        Meal result = mealDao.listMealById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No meal with that id.");
        } else {
            return result;
        }
    }


    public Mealplan createMealplan(Mealplan newMealPlan) {
        try {
            return mealplanDao.createMealplan(newMealPlan);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    // TODO this method is created to have a modifyMealplan method in place to satisfy the MVP.
    // however should check if updating a mealplan is simply adding or removing meals???
    // also needs to find a better name for the boolean variable
    public Mealplan modifyMealplan(int mealplanId, int mealId) {
        boolean addOrRemove = false;
        if (addOrRemove) {
            mealplanDao.addMealToMealplan(mealplanId, mealId);
        } else {
            mealplanDao.removeMealFromMealplan(mealplanId, mealId);
        }
        return mealplanDao.listMealplanById(mealplanId);
    }





//    public Meal createMeal(Meal newMeal) {
//        Meal myMeal = new Meal();
//        try {
//            myMeal = mealDao.createMeal(newMeal); // wait to see what's in there in mealDao
//            return myMeal;
//        } catch(DaoException e) {
//            throw new ServiceException("An error has occurred: " + e.getMessage());
//        }
//    }

// update meal is either add or remove recipe
//    public Meal updateMeal(int id, Meal updatedMeal) {
//        updatedMeal.setMealId(id);
//        if (mealDao.updateMeal(updatedMeal)) {
//            return updatedMeal;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found to update.");
//        }
//    }






}

