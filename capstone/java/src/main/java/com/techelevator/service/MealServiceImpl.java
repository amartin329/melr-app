package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class MealServiceImpl implements MealService {

    private MealDao mealDao;
    private UserDao userDao;
    private MealplanDao mealplanDao;
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;

    public MealServiceImpl(UserDao userDao, IngredientDao ingredientDao, RecipeDao recipeDao,
                             MealDao mealDao, MealplanDao mealplanDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.mealDao = mealDao;
        this.mealplanDao = mealplanDao;

    }


    public List<Meal> getMeals() throws InterruptedException {
//        Thread.sleep(2000); //Simulated loading time

        return mealDao.listAllMeals();
    }


    public Meal getMealById(int id) throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time

        Meal result = mealDao.listMealById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No meal with that id.");
        } else {
            return result;
        }
    }

    public Meal createMeal(Meal meal) {
        try {
            return mealDao.createMeal(meal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Meal updateMealInfo(int id, Meal updatedMeal) {
        updatedMeal.setMealId(id);
        if (mealDao.updateMealInfo(updatedMeal)){
            return updatedMeal;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found to update.");
        }
    }

    public int addRecipeToMeal(int mealId, int recipeId) {
        try {
            return mealDao.addRecipeToMeal(mealId, recipeId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public int removeRecipeFromMeal(int mealId, int recipeId) {
        try {
                return mealDao.removeRecipeFromMeal(mealId, recipeId);
        } catch (DaoException e) {
                throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
}
