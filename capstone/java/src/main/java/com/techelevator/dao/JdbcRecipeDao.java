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
public class JdbcRecipeDao implements RecipeDao {
    private final JdbcTemplate jdbcTemplate;
    private IngredientDao ingredientDao;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate, IngredientDao ingredientDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.ingredientDao = ingredientDao;
    }
    /** This method will list all the recipes available for use for the authenticated user
     * corresponding to the GET operation at endpoint "/recipes" in the RecipeController **/
    @Override
    public List<Recipe> listAllRecipes(int userId){
        List<Recipe> allRecipes = new ArrayList<>();
        String sql = "SELECT recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, " +
                "prep_time, instruction, favorited " +
                "FROM recipe WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
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


    /** This method will give details of a recipe (by its id) including its metadata and its ingredientList
     * corresponding to the GET operation at endpoint "/recipes/{id}" in the RecipeController **/
    @Override
    public Recipe getRecipeDetailsById(int recId, int userId){
        Recipe recipe = new Recipe();
        String sql = "SELECT recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, " +
                "prep_time, instruction, favorited " +
                "FROM recipe " +
                "WHERE recipe_id = ? AND user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recId, userId);
            if (results.next()) {
                recipe = mapRowToRecipe(results);
                recipe.setIngredientList(getIngredientListForRecipe(recipe.getRecipeId()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
        }
        return recipe;
    }

    /** This method is to create a recipe with its metadata and its ingredientList
     * corresponding to the POST operation at endpoint "/recipes" in the RecipeController **/
    @Override
    public Recipe createRecipe(Recipe recipe){
        String sql = "INSERT INTO recipe (recipe_type_id, recipe_tag_id, recipe_name, picture_path, prep_time, instruction, favorited, user_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING recipe_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, recipe.getRecipeTypeId(), recipe.getRecipeTagId(), recipe.getRecipeName(),
                    recipe.getPicturePath(), recipe.getPrepTime(), recipe.getInstruction(), recipe.isFavorited(), recipe.getUserId());
            recipe.setRecipeId(newId);
//            recipe.setIngredientList(getIngredientListForRecipe(recipe.getRecipeId()));
//            if(recipe.getIngredientList() != null) {
//                for (Ingredient ingredient : recipe.getIngredientList()) {
//                    ingredient = ingredientDao.createIngredient(ingredient);
//                    addIngredientToRecipe(recipe.getRecipeId(), ingredient.getIngId());
//                }
//            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getRecipeDetailsById(recipe.getRecipeId(), recipe.getUserId());
    }


    /** This method is first in the series of 3 methods for modifying a recipe, just to update metadata
     * of a recipe corresponding to the PUT operation at endpoint "/recipes/{id}" in the RecipeController**/
    public boolean updateRecipeInfo(Recipe recipe, int userId){
        int rowAffected;
        String sql = "UPDATE recipe " +
                "SET recipe_type_id = ?, recipe_tag_id = ?, recipe_name = ?, picture_path = ?, prep_time = ?, instruction = ?, favorited = ? " +
                "WHERE recipe_id = ? AND user_id = ?;";
        try {
            rowAffected = jdbcTemplate.update(sql, recipe.getRecipeTypeId(), recipe.getRecipeTagId(), recipe.getRecipeName(),
                    recipe.getPicturePath(), recipe.getPrepTime(), recipe.getInstruction(), recipe.isFavorited(), recipe.getRecipeId(), userId);
            recipe.setIngredientList(getIngredientListForRecipe(recipe.getRecipeId()));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowAffected == 1;
    }


    /** This method is second in the series of 3 methods for modifying a recipe when user wants to add an ingredient to a recipe
     * corresponding to the POST operation at endpoint "/recipes/{id}/modify/{id}" in the RecipeController. It will actually create a linkage
     * between that ingredient with the recipe and shows up in the join table recipe_ing.
     * Here the quantity and the measurement unit are put in as params, that means when working with Postman we should add these params in the request
     * like this "/recipes/{id}/modify/{id}?msmId={msmId}&quantity={quantity}". They can be changed at the front end to what user likes.
     * It's also a supporting method for creating recipe**/
    public int addIngredientToRecipe(int recipeId, int ingId, int msmId, double quantity, int userId) {
        int rowsAffected;
        String sql = "INSERT INTO recipe_ing (recipe_id, ing_id, msm_id, quantity) VALUES (?, ?, ?, ?);";
        try {
            rowsAffected = jdbcTemplate.update(sql, recipeId, ingId, msmId, quantity);
//            getRecipeDetailsById(recipeId, userId).setIngredientList(getIngredientListForRecipe(recipeId));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    /** This method is third in the series of 3 methods for modifying a recipe when user wants to remove an ingredient from a recipe
     * corresponding to the DELETE operation at endpoint "/recipes/{id}/modify/{id}" **/
    public int removeIngredientFromRecipe(int recipeId, int ingredientId) {
        int rowsAffected;
        String sql = "DELETE FROM recipe_ing WHERE recipe_id = ? AND ing_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, recipeId, ingredientId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }


    /**Supporting methods**/
    /** this method will list the ingredients in a particular recipe by recipe **/
    @Override
    public List<Ingredient> getIngredientListForRecipe(int recId) {
        List<Ingredient> recIngs = new ArrayList<>();
        String sql = "SELECT i.ing_id, i.ing_type_id, i.ing_name, i.nutrition_id, m.msm_unit, ri.quantity " +
                "FROM ingredient i " +
                "JOIN ingredient_type it ON i.ing_type_id = it.ing_type_id " +
                "JOIN nutrition nu ON i.nutrition_id = nu.nutrition_id " +
                "JOIN recipe_ing ri ON i.ing_id = ri.ing_id " +
                "JOIN measurement m ON ri.msm_id = m.msm_id " +
                "JOIN recipe r ON r.recipe_id = ri.recipe_id " +
                "WHERE r.recipe_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recId);
            while (results.next()) {
                Ingredient ingredient = mapRowToIngredient(results);
                ingredient.setNutrition(getNutritionForIngredient(ingredient.getIngId()));
                recIngs.add(ingredient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return recIngs;
    }

    /** This method gets the nutrition facts for an ingredient**/
    @Override
    public Nutrition getNutritionForIngredient(int ingId){
        Nutrition ingNutrition = null;
        String sql = "SELECT nu.nutrition_id, nu.calories, nu.protein, nu.carb, nu.fat " +
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




    /** Mapping methods **/
    public Ingredient mapRowToIngredient(SqlRowSet rs){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngId(rs.getInt("ing_id"));
        ingredient.setIngTypeId(rs.getInt("ing_type_id"));
        ingredient.setIngName(rs.getString("ing_name"));
        ingredient.setNutritionId(rs.getInt("nutrition_id"));
        ingredient.setNutrition(getNutritionForIngredient(rs.getInt("ing_id")));
        ingredient.setMsmUnit(rs.getString("msm_unit"));
        ingredient.setQuantity(rs.getDouble("quantity"));
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
        recipe.setRecipeTypeId(rs.getInt("recipe_type_id"));
        recipe.setRecipeTagId(rs.getInt("recipe_tag_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setPicturePath(rs.getString("picture_path"));
        recipe.setPrepTime(rs.getInt("prep_time"));
        recipe.setInstruction(rs.getString("instruction"));
        recipe.setFavorited(rs.getBoolean("favorited"));
        return recipe;
    }



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

    // not sure if this supporting method is needed
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
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return nutritionList;
    }


}
