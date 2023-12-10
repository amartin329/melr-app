package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIngredientDao implements IngredientDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //this method just lists all the ingredients that the logged-in user has in library
    @Override
    public List<Ingredient> listAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT i.ing_id, i.ing_name, i.ing_type_id, i.nutrition_id " +
                "FROM ingredient i;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Ingredient ingredient = mapRowToIngredient(results);
                ingredient.setNutrition(getNutritionForIngredient(ingredient.getIngId()));
                ingredients.add(ingredient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return ingredients;
    }

    // this method will list the details of a particular ingredient by its id
    @Override
    public Ingredient getIngredientById(int ingId) {
        Ingredient ingredient = null;
        String sql = "SELECT i.ing_name, i.ing_type_id, i.nutrition_id " +
                "FROM ingredient i " +
                "WHERE i.ing_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ingId);
            if (results.next()) {
                ingredient = mapRowToIngredient(results);
                ingredient.setNutrition(getNutritionForIngredient(ingId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return ingredient;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO ingredient (ing_type_id, ing_name, nutrition_id) " +
                "VALUES (?, ?, ?) RETURNING ing_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, ingredient.getIngTypeId(), ingredient.getIngName(), ingredient.getNutritionId());
            ingredient.setIngId(newId);
            ingredient.setNutrition(getNutritionForIngredient(newId));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return ingredient;
    }

    public boolean updateIngredient(Ingredient ingredient){
        int rowAffected;
        String sql = "UPDATE ingredient " +
                "SET ing_type_id = ?, ing_name = ?, nutrition_id = ?,  " +
                "WHERE ing_id = ?;";
        try {
            rowAffected = jdbcTemplate.update(sql, ingredient.getIngTypeId(), ingredient.getIngName(), ingredient.getNutritionId(), ingredient.getIngId());
            ingredient.setNutrition(getNutritionForIngredient(ingredient.getIngId()));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowAffected == 1;
    }


    @Override
    public Nutrition getNutritionForIngredient(int ingId){
        Nutrition ingNutrition = null;
        String sql = "SELECT nu.calories, nu.protein, nu.carb, nu.fat " +
                "FROM nutrition nu " +
                "JOIN ingredient i ON i.nutrition_id = nu.nutrition_id " +
                "WHERE i.ing_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ingId);
            if (results.next()) {
                ingNutrition = mapRowToNutrition(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return ingNutrition;

    }

    public Ingredient mapRowToIngredient(SqlRowSet rs){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngId(rs.getInt("ing_id"));
        ingredient.setIngTypeId(rs.getInt("ing_type_id"));
        ingredient.setIngName(rs.getString("ing_name"));
        ingredient.setNutritionId(rs.getInt("nutrition_id"));
        return ingredient;
    }

    public Nutrition mapRowToNutrition(SqlRowSet rs){
        Nutrition nutrition = new Nutrition();
        nutrition.setNutritionId(rs.getInt("nutrition_id"));
        nutrition.setCalories(rs.getDouble("calories"));
        nutrition.setProtein(rs.getDouble("protein"));
        nutrition.setCarb(rs.getDouble("carb"));
        nutrition.setFat(rs.getDouble("fat"));
        return nutrition;
    }

    // may not be needed
    @Override
    public int deleteIngredient(int ing_id){
        int rowsAffected;
        String sql = "DELETE FROM ingredient WHERE ing_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, ing_id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return rowsAffected;
    }

}