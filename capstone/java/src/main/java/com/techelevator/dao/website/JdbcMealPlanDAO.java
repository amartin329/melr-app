package com.techelevator.dao.website;

import com.techelevator.exception.DaoException;
import com.techelevator.model.website.MealPlan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMealPlanDAO implements MealPlanDAO {


private JdbcTemplate jdbcTemplate;

public JdbcMealPlanDAO(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

    @Override
    public MealPlan createMealPlan(MealPlan mealPlan) {
    String sql = "INSERT INTO mealplan (mealplan_name, mealplan_type_id) VALUES (?, ?) RETURNING mealplan_id;";

    try {
        int newId = jdbcTemplate.queryForObject(sql, int.class, mealPlan.getMealplan_name(), mealPlan.getMealplan_type_id());
        mealPlan.setMealplan_id(newId);
    } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
      return mealPlan;
    }

    //TODO updateMealPlan() <<<<<<<<<<

    @Override
    public int deleteMealPlan(int mealplan_id) {
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
    public List<MealPlan> listMealPlanByTypeId(int mealplan_type_id) {
    List<MealPlan> result = new ArrayList<>();
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_type_id = ?;";
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplan_type_id);
    if (rowSet.next()) {
        MealPlan mealPlan = mapRowToMealPlan(rowSet);
        result.add(mealPlan);
        }
        return result;

    }

    @Override
    public List<MealPlan> listAllMealPlans() {
    List<MealPlan> mealPlans = new ArrayList<>();
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan;";
    try {
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            mealPlans.add(mapRowToMealPlan(results));
        }
    } catch (CannotGetJdbcConnectionException e){
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
    return mealPlans;
    }


    @Override
    public MealPlan listMealPlanById(int mealplan_id) {
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_id = ?;";
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplan_id);
    if (rowSet.next()) {
        MealPlan mealPlan = mapRowToMealPlan(rowSet);
        return mealPlan;
    } else {
        return null;
        }
    }


    private MealPlan mapRowToMealPlan(SqlRowSet rowSet) {
        MealPlan result = new MealPlan();
        result.setMealplan_id(rowSet.getInt("mealplan_id"));
        result.setMealplan_name(rowSet.getString("mealplan_name"));
        result.setMealplan_type_id(rowSet.getInt("mealplan_type_id"));
        return result;
    }

}
