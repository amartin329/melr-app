package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.management.loading.PrivateClassLoader;
import java.security.Principal;
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

    public List<Mealplan> getMealplans(Principal user) throws InterruptedException {
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
//        Thread.sleep(1000); //Simulated loading time
        return mealplanDao.listAllMealplans(userId);
    }


    public Mealplan getMealplanById(int id, Principal user) throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
        Mealplan result = mealplanDao.listMealplanById(id, userId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealplan with that id.");
        } else {
            return result;
        }
    }

    public Mealplan createMealplan(Mealplan newMealPlan, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            newMealPlan.setUserId(authUser.getId());
            return mealplanDao.createMealplan(newMealPlan);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Mealplan updateMealplanInfo(int id, Mealplan updatedMealplan, Principal user) {
        updatedMealplan.setMealplanId(id);
        User authUser = userDao.getUserByUsername(user.getName());
        int userId = authUser.getId();
            if (mealplanDao.updateMealplanInfo(updatedMealplan, userId)){
                return updatedMealplan;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mealplan not found to update.");
            }
    }

    public int addMealToMealplan(int mealplanId, int mealId, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Mealplan result = mealplanDao.listMealplanById(mealplanId, userId);
            if(result != null){
                return mealplanDao.addMealToMealplan(mealplanId, mealId);
            }else{
                throw new DaoException("Error: Cannot edit meal plan.");
            }

        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public int removeMealFromMealplan(int mealplanId, int mealId, Principal user) {
        try {
            User authUser = userDao.getUserByUsername(user.getName());
            int userId = authUser.getId();
            Mealplan result = mealplanDao.listMealplanById(mealplanId, userId);
            if(result != null){
                return mealplanDao.removeMealFromMealplan(mealplanId, mealId);
            }else{
                throw new DaoException("Error: Cannot edit meal plan.");
            }
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
}
