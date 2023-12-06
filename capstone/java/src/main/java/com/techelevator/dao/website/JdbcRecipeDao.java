package com.techelevator.dao.website;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcRecipeDao implements RecipeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //I left 'favorited' out of this, since it seems like that would act funny
    //TODO talk about this

    @Override
    public Recipe createRecipe(Recipe recipe){
        String sql = "INSERT INTO recipe (recipe_type_id, recipe_tag_id, recipe_name, picture_path, prep_time, instruction) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING recipe_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, recipe.getRecipeType(), recipe.getRecipeTag(), recipe.getRecipeName(),
                    recipe.getPicturePath(), recipe.getPrepTime(), recipe.getInstruction());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return recipe;
    }

    //TODO updateIngredient() <<<<<<<<<<

    //TODO deleteIngredient() <<<<<<<<<<

    @Override
    public int deleteRecipe(int recipe_id){
        int rowsAffected;
        String sql = "DELETE FROM recipe WHERE meal_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, recipe_id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    @Override
    public List<Recipe> listAllRecipes(){
        List<Recipe> allRecipes = new ArrayList<>();
        String sql = "SELECT r.recipe_id, r.recipe_name, ry.recipe_type_desc, ra.recipe_tag_desc, r.picture_path, " +
                "r.prep_time, r.instruction, r.favorited " +
                "FROM recipe r " +
                "JOIN recipe_type ry ON r.recipe_type_id = ry.recipe_type_id " +
                "JOIN recipe_tag ra ON r.recipe_tag_id = ra.recipe_tag_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Recipe recipe = mapRowToRecipe(results);
                recipe.setIngredientList(getIngredientListForRecipe(recipe.getRecipeId()));
                allRecipes.add(recipe);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return allRecipes;
    }

    //this method will get the details of a particular recipe by id

    @Override
    public Recipe getRecipeDetailsById(int recId){
        Recipe recipe = new Recipe();
        String sql = "SELECT r.recipe_id, r.recipe_name, ry.recipe_type_desc, ra.recipe_tag_desc, r.picture_path, " +
                "r.prep_time, r.instruction, r.favorited " +
                "FROM recipe r " +
                "JOIN recipe_type ry ON r.recipe_type_id = ry.recipe_type_id " +
                "JOIN recipe_tag ra ON r.recipe_tag_id = ra.recipe_tag_id " +
                "WHERE r.recipe_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recId);
            if (results.next()) {
                recipe = mapRowToRecipe(results);
                recipe.setIngredientList(getIngredientListForRecipe(recipe.getRecipeId()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return recipe;
    }

    //this method will list the ingredients in a particular recipe by recipe Id

    @Override
    public List<Ingredient> getIngredientListForRecipe(int recId) {
        List<Ingredient> recIngs = new ArrayList<>();
        String sql = "SELECT i.ing_id, i.ing_name, it.ing_type, ri.quantity, m.msm_unit " +
                "FROM ingredient i " +
                "JOIN ingredient_type it ON i.ing_type_id = it.ing_type_id " +
                "JOIN recipe_ing ri ON i.ing_id = ri.ing_id " +
                "JOIN measurement m ON ri.msm_id = m.msm_id " +
                "JOIN recipe r ON r.recipe_id = ri.recipe_id " +
                "WHERE ri.recipe_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recId);
            while (results.next()) {
                Ingredient ingredient = mapRowToIngredient(results);
                ingredient.setNutrition(getNutritionForIngredient(ingredient.getIngId()));
                recIngs.add(ingredient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return recIngs;
    }

    @Override
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

    //this method will list the total Nutrition of ingredients of a particular recipe by recipe Id

    @Override
    public List<Nutrition> getNutritionForRecipe(int recId){
        List<Nutrition> nutritionList = new ArrayList<>();
        String sql = "SELECT i.ing_id, i.ing_name, it.ing_type, nu.calories, nu.protein, nu.carb, nu.fat, ri.quantity, m.msm_unit " +
                "FROM ingredient i " +
                "JOIN ingredient_type it ON i.ing_type_id = it.ing_type_id " +
                "JOIN nutrition nu ON i.nutrition_id = nu.nutrition_id" +
                "JOIN recipe_ing ri ON i.ing_id = ri.ing_id" +
                "JOIN measurement m ON ri.msm_id = m.msm_id" +
                "JOIN recipe r ON r.recipe_id = ri.recipe_id" +
                "WHERE ri.recipe_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recId);
            if (results.next()) {
                Nutrition ingTotalNutrition = mapRowToTotalNutrition(results);
                nutritionList.add(ingTotalNutrition);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return nutritionList;
    }

    @Override
    public List<Recipe> listRecipeByMealId(int recipe_id) {
        List<Recipe> result = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, " +
                "prep_time, instruction, favorited FROM recipe WHERE recipe_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipe_id);
        while (rowSet.next()) {
            Recipe recipe = mapRowToRecipe(rowSet);
            result.add(recipe);
        }
        return result;
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

    public Nutrition mapRowToTotalNutrition(SqlRowSet rs){
        Nutrition nutrition = new Nutrition();
        nutrition.setNutritionId(rs.getInt("nutrition_id"));
        nutrition.setCalories(rs.getDouble("calories") * rs.getDouble("quantity"));
        nutrition.setProtein(rs.getDouble("protein") * rs.getDouble("quantity"));
        nutrition.setCarb(rs.getDouble("carb") * rs.getDouble("quantity"));
        nutrition.setFat(rs.getDouble("fat") * rs.getDouble("quantity"));
        return nutrition;
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

}
