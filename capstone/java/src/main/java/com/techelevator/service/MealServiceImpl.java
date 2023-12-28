package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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


    public List<Meal> getMeals(Principal user) throws InterruptedException {
//        Thread.sleep(2000); //Simulated loading time
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
        return mealDao.listAllMeals(userId);
    }


    public Meal getMealById(int id, Principal user) throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
        Meal result = mealDao.listMealById(id, userId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No meal with that id.");
        } else {
            return result;
        }
    }

    public Meal createMeal(Meal meal, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            meal.setUserId(authUser.getId());
            return mealDao.createMeal(meal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Meal updateMealInfo(int id, Meal updatedMeal, Principal user) {
        updatedMeal.setMealId(id);
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
        if (mealDao.updateMealInfo(updatedMeal, userId)){
            return updatedMeal;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found to update.");
        }
    }

    public int addRecipeToMeal(int mealId, int recipeId, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Meal result = mealDao.listMealById(mealId, userId);
            if(result != null){
                return mealDao.addRecipeToMeal(mealId, recipeId);
            }else{
                throw new DaoException("Error: Cannot edit meal plan.");
            }
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public int removeRecipeFromMeal(int mealId, int recipeId, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Meal result = mealDao.listMealById(mealId, userId);
            if(result !=null){
                return mealDao.removeRecipeFromMeal(mealId, recipeId);

            }else{
                throw new DaoException("Error: Cannot edit meal plan.");
            }
        } catch (DaoException e) {
                throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
}
