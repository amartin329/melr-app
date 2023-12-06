package com.techelevator.dao.website;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMealplanDao implements MealplanDao {


private final JdbcTemplate jdbcTemplate;
private MealDao mealDao;

public JdbcMealplanDao(JdbcTemplate jdbcTemplate, MealDao mealDao){
    this.jdbcTemplate = jdbcTemplate;
    this.mealDao = mealDao;
}

    @Override
    public Mealplan createMealplan(Mealplan mealplan) {
    String sql = "INSERT INTO mealplan (mealplan_name, mealplan_type_id) VALUES (?, ?) RETURNING mealplan_id;";

    try {
        int newId = jdbcTemplate.queryForObject(sql, int.class, mealplan.getMealplanName(), mealplan.getMealplanTypeId());
        mealplan.setMealplanId(newId);
        if(mealplan.getMealList() != null) {
            for (Meal meal : mealplan.getMealList()) {
                meal = mealDao.createMeal(mealplan.getMealplanId(), meal);
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

    //TODO updateMealPlan() <<<<<<<<<<

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


    @Override
    public Mealplan listMealplanById(int mealplan_id) {
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_id = ?;";
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplan_id);
    if (rowSet.next()) {
        Mealplan mealplan = mapRowToMealPlan(rowSet);
        return mealplan;
    } else {
        return null;
        }
    }


    private Mealplan mapRowToMealPlan(SqlRowSet rowSet) {
        Mealplan result = new Mealplan();
        result.setMealplanId(rowSet.getInt("mealplan_id"));
        result.setMealplanName(rowSet.getString("mealplan_name"));
        result.setMealplanTypeId(rowSet.getInt("mealplan_type_id"));
        return result;
    }

}
