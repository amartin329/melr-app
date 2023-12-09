package com.techelevator.dao;

import com.techelevator.exception.DaoException;
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

    /** This method will list all the meals available for use for the authenticated user
     * corresponding to the GET operation at endpoint "/meals/" in the controller **/
    @Override
    public List<Meal> listAllMeals() {
        List<Meal> result = new ArrayList<>();
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            if (rowSet.next()) {
                Meal meal = mapRowToMeal(rowSet);
                meal.setRecipeList(listRecipesByMealId(meal.getMealId()));
                result.add(meal);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return result;
    }

    /** This method will give details of a meal (by its id) including its metadata and its recipeList
     * corresponding to the GET operation at endpoint "/meals/{id}" in the controller **/
    @Override
    public Meal listMealById(int meal_id) {
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal WHERE meal_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, meal_id);
        if (rowSet.next()) {
            Meal meal = mapRowToMeal(rowSet);
            meal.setRecipeList(listRecipesByMealId(meal.getMealId()));
            return meal;
        } else {
            return null;
        }

    }

    /** This method is to create a meal with its metadata and its recipeList
     * corresponding to the POST operation at endpoint "/meals" in the controller **/
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
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return meal;
    }

    /** This method is first in the series of 3 methods for modifying a meal when user wants to update metadata
     * of a meal corresponding to the PUT operation at endpoint "/meals/{id}/modify" **/
    public Meal updateMealInfo(Meal meal){
        int rowAffected;
        String sql = "UPDATE meal " +
                "SET meal_type_id = ?, meal_name = ? " +
                "WHERE meal_id = ?;";
        try {
            rowAffected = jdbcTemplate.update(sql, meal.getMealTypeId(), meal.getMealName(), meal.getMealId());
            if (rowAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return listMealById(meal.getMealId());
    }

    /** This method is second in the series of 3 methods for modifying a meal when user wants to add a recipe to a meal
     * corresponding to the POST operation at endpoint "/meals/{id}/modify". It's also a supporting method for creating meal**/
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

    /** This method is third in the series of 3 methods for modifying a meal when user wants to remove a recipe from a meal
     * corresponding to the DELETE operation at endpoint "/meals/{id}/modify" **/
    public int removeRecipeFromMeal(int mealId, int recipeId) {
        int rowsAffected;
        String sql = "DELETE FROM recipe_meal WHERE meal_id = ? AND recipe_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, mealId, recipeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    /** This is a supporting method to list all recipes of a meal **/
    @Override
    public List<Recipe> listRecipesByMealId(int meal_id) {
        List<Recipe> result = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, " +
                "prep_time, instruction, favorited FROM recipe " +
                "WHERE recipe_id = (SELECT recipe_id FROM recipe_meal WHERE meal_id = ?);";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, meal_id);
        while (rowSet.next()) {
            Recipe recipe = mapRowToRecipe(rowSet);
            recipe.setIngredientList(recipeDao.getIngredientListForRecipe(recipe.getRecipeId()));
            result.add(recipe);
        }
        return result;
    }

    /** Mapping methods**/
    private Meal mapRowToMeal(SqlRowSet rowSet) {
        Meal result = new Meal();
        result.setMealId(rowSet.getInt("meal_id"));
        result.setMealName(rowSet.getString("meal_name"));
        result.setMealTypeId(rowSet.getInt("meal_type_id"));
        return result;
    }

    public Recipe mapRowToRecipe(SqlRowSet rs){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setRecipeType(rs.getString("recipe_type_desc"));
        recipe.setRecipeTag(rs.getString("recipe_tag_desc"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setPicturePath(rs.getString("picture_path"));
        recipe.setPrepTime(rs.getInt("prep_time"));
        recipe.setInstruction(rs.getString("instruction"));
        recipe.setFavorited(rs.getBoolean("favorited"));
        return recipe;
    }


    // Not sure if these two methods are needed
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


}