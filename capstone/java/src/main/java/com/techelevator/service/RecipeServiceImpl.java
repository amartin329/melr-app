package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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

    public List<Recipe> getRecipes(Principal user) {
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
        try{
           return recipeDao.listAllRecipes(userId);

        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Recipe getRecipeById(int id, Principal user) {
        try{
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Recipe recipe = recipeDao.getRecipeDetailsById(id, userId);
            if (recipe == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return recipe;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Recipe createRecipe(Recipe recipe, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            recipe.setUserId(authUser.getId());
            return recipeDao.createRecipe(recipe);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


    public Recipe updateRecipeInfo(int id, Recipe updatedRecipe, Principal user) {
        updatedRecipe.setRecipeId(id);
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
            if(recipeDao.updateRecipeInfo(updatedRecipe, userId)){
                return updatedRecipe;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found to update.");
            }
    }


    public int addIngredientToRecipe(int recipeId, int ingId, int msmId, double quantity, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Recipe result = recipeDao.getRecipeDetailsById(recipeId, userId);
            if(result !=null){
                return recipeDao.addIngredientToRecipe(recipeId, ingId, msmId, quantity, userId);
            }else{
                throw new DaoException("Error: Cannot edit recipe.");
            }
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public int removeIngredientFromRecipe(int recipeId, int ingId, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Recipe result = recipeDao.getRecipeDetailsById(recipeId, userId);
            if(result!=null){
                return recipeDao.removeIngredientFromRecipe(recipeId, ingId);

            }else{
                throw new DaoException("Error: Cannot edit recipe.");
            }
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

}
