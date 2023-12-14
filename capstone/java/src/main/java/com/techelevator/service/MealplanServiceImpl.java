package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class MealplanServiceImpl implements MealplanService{
    private MealDao mealDao;
    private UserDao userDao;
    private MealplanDao mealplanDao;
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;

    public MealplanServiceImpl(UserDao userDao, IngredientDao ingredientDao, RecipeDao recipeDao,
                             MealDao mealDao, MealplanDao mealplanDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.mealDao = mealDao;
        this.mealplanDao = mealplanDao;

    }

    public List<Mealplan> getMealplans() throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time
        return mealplanDao.listAllMealplans();
    }


    public Mealplan getMealplanById(int id) throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time
        Mealplan result = mealplanDao.listMealplanById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealplan with that id.");
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

    public Mealplan updateMealplanInfo(int id, Mealplan updatedMealplan) {
        updatedMealplan.setMealplanId(id);
            if (mealplanDao.updateMealplanInfo(updatedMealplan)){
                return updatedMealplan;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mealplan not found to update.");
            }
    }

    public int addMealToMealplan(int mealplanId, int mealId) {
        try {
            return mealplanDao.addMealToMealplan(mealplanId, mealId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public int removeMealFromMealplan(int mealplanId, int mealId) {
        try {
            return mealplanDao.removeMealFromMealplan(mealplanId, mealId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
}
