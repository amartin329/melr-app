package com.techelevator.dao.website;

import com.techelevator.exception.DaoException;
import com.techelevator.model.website.Meal;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMealDAO implements MealDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcMealDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Meal createMeal(Meal meal) {
        String sql = "INSERT INTO meal (meal_name, meal_type_id) VALUES (?, ?) RETURNING meal_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, meal.getMeal_name(), meal.getMeal_type_id());
            meal.setMeal_id(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return meal;
    }

    //TODO updateMeal() <<<<<<<<<<

    @Override
    public int deleteMeal(int meal_id) {
        int rowsAffected;
        String sql = "DELETE FROM meal WHERE meal_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, meal_id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    @Override
    public Meal listMealById(int meal_id) {
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal WHERE meal_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, meal_id);
        if (rowSet.next()) {
            Meal meal = mapRowToMeal(rowSet);
            return meal;
        } else {
            return null;
        }

    }

    @Override
    public List<Meal> listMealByTypeId(int meal_type_id) {
        List<Meal> result = new ArrayList<>();
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal WHERE meal_type_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, meal_type_id);
            if (rowSet.next()) {
                Meal meal = mapRowToMeal(rowSet);
                result.add(meal);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return result;
    }

    @Override
    public List<Meal> listAllMeals() {
        List<Meal> result = new ArrayList<>();
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            if (rowSet.next()) {
                Meal meal = mapRowToMeal(rowSet);
                result.add(meal);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return result;
    }

    private Meal mapRowToMeal(SqlRowSet rowSet) {
        Meal result = new Meal();
        result.setMeal_id(rowSet.getInt("meal_id"));
        result.setMeal_name(rowSet.getString("meal_name"));
        result.setMeal_type_id(rowSet.getInt("meal_type_id"));
        return result;
    }


}





