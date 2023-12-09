package com.techelevator.service;

import com.techelevator.dao.website.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Ingredient;
import org.springframework.stereotype.Component;

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
            Ingredient ingredient = ingredientDao.getIngredientById(id);
            if (ingredient == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return ingredient;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


}
