package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
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

    /** This method will list all the mealplans available for use for the authenticated user
     * corresponding to the GET operation at endpoint "/mealplans/" in the controller **/
    @Override
    public List<Mealplan> listAllMealplans() {
        List<Mealplan> mealplans = new ArrayList<>();
        String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                mealplans.add(mapRowToMealplan(results));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return mealplans;
    }


    /** This method will give details of a mealplan (by its id) including its metadata and its mealList
     * corresponding to the GET operation at endpoint "/mealplans/{id}" in the controller **/
    @Override
    public Mealplan listMealplanById(int mealplanId) {
        String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplanId);
        if (rowSet.next()) {
            Mealplan mealplan = mapRowToMealplan(rowSet);
            mealplan.setMealList(getMealsForMealplanId(mealplan.getMealplanId()));
            return mealplan;
        } else {
            return null;
        }
    }

    /** This method is to create a mealplan with its metadata and its mealList
     * corresponding to the POST operation at endpoint "/mealplans" in the controller **/
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
            }
        }
    } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
      return mealplan;
    }

    /** This method is first in the series of 3 methods for modifying a mealplan when user wants to update metadata
     * of a mealplan corresponding to the PUT operation at endpoint "/mealplans/{id}" **/
    public boolean updateMealplanInfo(Mealplan mealplan){
        int rowAffected;
        String sql = "UPDATE mealplan " +
                "SET mealplan_type_id = ?, mealplan_name = ? " +
                "WHERE mealplan_id = ?;";
        try {
            rowAffected = jdbcTemplate.update(sql, mealplan.getMealplanTypeId(), mealplan.getMealplanName(), mealplan.getMealplanId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowAffected == 1;
    }

    /** This method is second in the series of 3 methods for modifying a mealplan when user wants to add a meal to a mealplan
     * corresponding to the POST operation at endpoint "/mealplans/{id}/modify/{id}". It's also a supporting method for creating mealplan**/
    public int addMealToMealplan(int mealplanId, int mealId) {
        int rowsAffected;
        String sql = "INSERT INTO meal_mealplan (mealplan_id, meal_id) VALUES (?, ?);";
        try {
            rowsAffected = jdbcTemplate.update(sql, mealplanId, mealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }
    /** This method is third in the series of 3 methods for modifying a mealplan when user wants to remove a meal to a mealplan
     * corresponding to the DELETE operation at endpoint "/mealplans/{id}/modify/{id}" **/
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


    /** This is a supporting method to list all meals of a mealplan **/
    public List<Meal> getMealsForMealplanId(int mealplanId){
        List<Meal> result = new ArrayList<>();
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal WHERE meal_id IN " +
                "(SELECT meal_id FROM meal_mealplan WHERE mealplan_id = ?);";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplanId);
            while (rowSet.next()) {
                Meal meal = mapRowToMeal(rowSet);
                meal.setRecipeList(mealDao.listRecipesByMealId(meal.getMealId()));
                result.add(meal);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
       return result;
   }

   /** Mapping methods **/
    private Meal mapRowToMeal(SqlRowSet rowSet) {
        Meal result = new Meal();
        result.setMealId(rowSet.getInt("meal_id"));
        result.setMealName(rowSet.getString("meal_name"));
        result.setMealTypeId(rowSet.getInt("meal_type_id"));
        return result;
    }

    private Mealplan mapRowToMealplan(SqlRowSet rowSet) {
        Mealplan result = new Mealplan();
        result.setMealplanId(rowSet.getInt("mealplan_id"));
        result.setMealplanName(rowSet.getString("mealplan_name"));
        result.setMealplanTypeId(rowSet.getInt("mealplan_type_id"));
        return result;
    }

    // Not sure if these methods are needed at all
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
    public List<Mealplan> listMealplanByTypeId(int mealplan_type_id) {
        List<Mealplan> result = new ArrayList<>();
        String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_type_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplan_type_id);
        if (rowSet.next()) {
            Mealplan mealPlan = mapRowToMealplan(rowSet);
            result.add(mealPlan);
        }
        return result;

    }

}
