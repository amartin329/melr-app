package com.techelevator.dao.website;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Meal;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcMealDao implements MealDao{
    private JdbcTemplate jdbcTemplate;
    private RecipeDao recipeDao;


    public JdbcMealDao(JdbcTemplate jdbcTemplate, RecipeDao recipeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeDao = recipeDao;
    }

// we need a createRecipe() method

    @Override
    public Meal createMeal(Meal meal) {
        String sql = "INSERT INTO meal (meal_name, meal_type_id) VALUES (?, ?) RETURNING mealId;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, meal.getMealName(), meal.getMealTypeId());
            meal.setMealId(newId);
            if(meal.getRecipeList() != null) {
                for (Recipe recipe : meal.getRecipeList()) {
                    recipe = recipeDao.createRecipe(recipe);
                    addRecipeToMeal(newId, recipe.getRecipeId());
                    // if we don't have meal ID here then this recipe list is just there independent
                    // not attaching to any particular meal
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return meal;
    }

    public int addRecipeToMeal(int mealId, int recipeId) {
        int rowsAffected;
        String sql = "INSERT INTO recipe_meal (recipe_id, meal_id) VALUES (?, ?);";
        try {
            rowsAffected = jdbcTemplate.update(sql, recipeId, mealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }



    // TODO Note for updateMeal(): we don't have updateMeal because if we want to change meal, it would be
    //  either delete or add recipe to meal. So I would think we would have a "delete recipe" button and
    //  an "add recipe" button for a meal<<<<<<<<<<

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
        result.setMealId(rowSet.getInt("meal_id"));
        result.setMealName(rowSet.getString("meal_name"));
        result.setMealTypeId(rowSet.getInt("meal_type_id"));
        return result;
    }


}
