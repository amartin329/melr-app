package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class JdbcIngredientDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //this method just lists all the ingredients that the logged-in user has in library
    public List<Ingredient> listAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT i.ing_id, i.ing_name, it.ing_type " +
                "FROM ingredient i " +
                "JOIN ingredient_type it ON i.ing_type_id = it.ing_type_id;";

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
    public Ingredient getIngredientById(int ingId) {
        Ingredient ingredient = null;
        String sql = "SELECT i.ing_name, it.ing_type " +
                "FROM ingredient i " +
                "JOIN ingredient_type it ON i.ing_type_id = it.ing_type_id " +
                "JOIN nutrition nu ON i.nutrition_id = nu.nutrition_id " +
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



    public Nutrition getNutritionForIngredient(int ingId){
        Nutrition ingNutrition = null;
        String sql = "SELECT nu.calories, nu.protein, nu.carb, nu.fat " +
                "FROM nutrition nu " +
                "JOIN ingredient i ON i.nutrition_id = nutrition_id " +
                "WHERE i.ing_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ingId);
            if (results.next()) {
                ingNutrition = mapRowToNutrition(results);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return ingNutrition;

    }



    public Ingredient mapRowToIngredient(SqlRowSet rs){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngId(rs.getInt("ing_id"));
        ingredient.setIngType(rs.getString("ing_type"));
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



}