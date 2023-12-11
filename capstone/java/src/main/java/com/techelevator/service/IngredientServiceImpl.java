package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientServiceImpl implements IngredientService{
    private MealDao mealDao;
    private UserDao userDao;
    private MealplanDao mealplanDao;
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;

    public IngredientServiceImpl(UserDao userDao, IngredientDao ingredientDao, RecipeDao recipeDao,
                             MealDao mealDao, MealplanDao mealplanDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.mealDao = mealDao;
        this.mealplanDao = mealplanDao;
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        try{
            allIngredients = ingredientDao.listAllIngredients();
            if (allIngredients == null) {
                throw new ServiceException("Ingredients not found.");
            } else {
                return allIngredients;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Ingredient getIngredientById(int id) {
        try{
            Ingredient ingredient = ingredientDao.getIngredientById(id);
            if (ingredient == null) {
                throw new ServiceException("Ingredient id: " + id + " was not found.");
            } else {
                return ingredient;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        try {
            return ingredientDao.createIngredient(ingredient);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Ingredient updateIngredient(int id, Ingredient updatedIngredient) {
        updatedIngredient.setIngId(id);
            if (ingredientDao.updateIngredient(updatedIngredient)) {
                return updatedIngredient;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found to update.");
            }
    }


}
