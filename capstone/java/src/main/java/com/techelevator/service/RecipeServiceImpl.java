package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeServiceImpl implements RecipeService {
    private MealDao mealDao;
    private UserDao userDao;
    private MealplanDao mealplanDao;
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;

    public RecipeServiceImpl(UserDao userDao, IngredientDao ingredientDao, RecipeDao recipeDao,
                              MealDao mealDao, MealplanDao mealplanDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.mealDao = mealDao;
        this.mealplanDao = mealplanDao;

    }

    public List<Recipe> getRecipes() {
        List<Recipe> allRecipes = new ArrayList<>();
        try{
            allRecipes = recipeDao.listAllRecipes();
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
            Recipe recipe = recipeDao.getRecipeDetailsById(id);
            if (recipe == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return recipe;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Recipe createRecipe(Recipe recipe) {
        try {
            return recipeDao.createRecipe(recipe);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


    public Recipe updateRecipeInfo(int id, Recipe updatedRecipe) {
        updatedRecipe.setRecipeId(id);
            if(recipeDao.updateRecipeInfo(updatedRecipe)){
                return updatedRecipe;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found to update.");
            }
    }


    public int addIngredientToRecipe(int recipeId, int ingId) {
        try {
            return recipeDao.addIngredientToRecipe(recipeId, ingId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public int removeIngredientFromRecipe(int recipeId, int ingId) {
        try {
            return recipeDao.removeIngredientFromRecipe(recipeId, ingId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

}
