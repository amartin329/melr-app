package com.techelevator.dao.website;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcMealplanDao implements MealplanDao {


private final JdbcTemplate jdbcTemplate;
private MealDao mealDao;
private RecipeDao recipeDao;

public JdbcMealplanDao(JdbcTemplate jdbcTemplate, MealDao mealDao, RecipeDao recipeDao){
    this.jdbcTemplate = jdbcTemplate;
    this.mealDao = mealDao;
    this.recipeDao = recipeDao;
}

    @Override
    public Mealplan createMealplan(Mealplan mealplan) {
    String sql = "INSERT INTO mealplan (mealplan_name, mealplan_type_id) VALUES (?, ?) RETURNING mealplan_id;";

    try {
        int newId = jdbcTemplate.queryForObject(sql, int.class, mealplan.getMealplanName(), mealplan.getMealplanTypeId());
        mealplan.setMealplanId(newId);
        if(mealplan.getMealList() != null) {
            for (Meal meal : mealplan.getMealList()) {
                meal = mealDao.createMeal(meal);
                addMealToMealplan(newId, meal.getMealId());
                // if we don't have mealplan ID here then this meal list is just there independent
                // not attaching to any particular mealplan
            }
        }
    } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
      return mealplan;
    }

    public int addMealToMealplan(int mealplanId, int mealId) {
        int rowsAffected;
        String sql = "INSERT INTO meal_mealplan (meal_id, mealplan_id) VALUES (?, ?);";
        try {
            rowsAffected = jdbcTemplate.update(sql, mealplanId, mealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    public int removeMealFromMealplan(int mealplanId, int mealId) {
        int rowsAffected;
        String sql = "DELETE FROM meal_mealplan WHERE mealplan_id = ? AND meal_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, mealplanId, mealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    //TODO Note for updateMealPlan(): We don't have updateMealplan because when we want to change mealplan, it's when
    // we either remove meal out of or add meal into mealplan. So I would think we would have a "delete meal" button and
    // an "add meal" button for a mealplan <<<<<<<<<<

    @Override
    public int deleteMealplan(int mealplan_id) {
        int rowsAffected;
        String sql = "DELETE FROM mealplan WHERE mealplan_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, mealplan_id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    @Override
    public List<Mealplan> listMealPlanByTypeId(int mealplan_type_id) {
    List<Mealplan> result = new ArrayList<>();
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_type_id = ?;";
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplan_type_id);
    if (rowSet.next()) {
        Mealplan mealPlan = mapRowToMealPlan(rowSet);
        result.add(mealPlan);
        }
        return result;

    }

    @Override
    public List<Mealplan> listAllMealplans() {
    List<Mealplan> mealplans = new ArrayList<>();
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan;";
    try {
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            mealplans.add(mapRowToMealPlan(results));
        }
    } catch (CannotGetJdbcConnectionException e){
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
    return mealplans;
    }


    // this get mealplan details by Id can be understood as list all the meals in the mealplan out.
    @Override
    public Mealplan listMealplanById(int mealplanId) {
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_id = ?;";
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplanId);
    if (rowSet.next()) {
        Mealplan mealplan = mapRowToMealPlan(rowSet);
        mealplan.setMealList(getMealsForMealplanId(mealplan.getMealplanId()));
        return mealplan;
    } else {
        return null;
        }
    }

   public List<Meal> getMealsForMealplanId(int mealplanId){
        List<Meal> result = new ArrayList<>();
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal WHERE meal_id = " +
                "(SELECT meal_id FROM meal_mealplan WHERE mealplan_id = ?);";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplanId);
        while (rowSet.next()) {
           Meal meal = mapRowToMeal(rowSet);
           meal.setRecipeList(recipeDao.listRecipeByMealId(meal.getMealId()));
           result.add(meal);
       }
       return result;
   }






    private Meal mapRowToMeal(SqlRowSet rowSet) {
        Meal result = new Meal();
        result.setMealId(rowSet.getInt("meal_id"));
        result.setMealName(rowSet.getString("meal_name"));
        result.setMealTypeId(rowSet.getInt("meal_type_id"));
        return result;
    }

    private Mealplan mapRowToMealPlan(SqlRowSet rowSet) {
        Mealplan result = new Mealplan();
        result.setMealplanId(rowSet.getInt("mealplan_id"));
        result.setMealplanName(rowSet.getString("mealplan_name"));
        result.setMealplanTypeId(rowSet.getInt("mealplan_type_id"));
        return result;
    }

}
