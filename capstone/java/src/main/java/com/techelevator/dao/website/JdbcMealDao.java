package com.techelevator.dao.website;

import com.techelevator.model.website.Meal;
import com.techelevator.model.website.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMealDao implements MealDao {

    // createMeal()
    // deleteMeal()
    // listRecipeByMealId()


private JdbcTemplate jdbcTemplate;

public JdbcMealDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}

// we need a createRecipe() method

    @Override
public Meal createMeal(Meal meal) {
    String sql = "INSERT INTO meal (meal_name, meal_type_id) VALUES (?, ?) RETURNING meal_id;";
    int newId = jdbcTemplate.queryForObject(sql, int.class, meal.getMeal_name(), meal.getMeal_type_id());
    meal.setMeal_id(newId);

    if (meal.getRecipes() != null && meal.getRecipes().size() > 0) {
        for (Recipe recipe : meal.getRecipes()) {
            recipe = createRecipe(meal.getId(), recipe);
        }
    }
    return meal;
}

@Override
    public boolean deleteMeal(int meal_id) {
        String sql = "DELETE FROM meal WHERE meal_id = ?;";
        jdbcTemplate.update(sql, meal_id);
        sql = "DELETE FROM recipe_meal WHERE meal_id = ?;";
        jdbcTemplate.update(sql, meal_id);
        sql = "DELETE FROM meal_mealplan WHERE meal_id = >;";
        jdbcTemplate.update(sql, meal_id);
        int count = jdbcTemplate.update(sql, meal_id);
        return count == 1;

    }

//listRecipeByMealId() goes here

    //TODO should this be in the Recipe jdbcDAO or the Meal jdbcDAO


//private List<Recipe> listRecipeByMealId(int recipe_id) {
//    List<Recipe> result = new ArrayList<>();
//    String sql = "SELECT recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, " +
//            "prep_time, instruction, favorited FROM recipe WHERE recipe_id = ?;";
//    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipe_id);
//    while (rowSet.next()) {
//        Recipe recipe = mapRowToRecipe(rowSet);
//        result.add(recipe);
//    }
//    return result;
//}


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


    private List<Meal> listMealByTypeId(int meal_type_id) {
        List<Meal> result = new ArrayList<>();
        String sql = "SELECT meal_id, meal_name, meal_type_id FROM meal WHERE meal_type_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, meal_type_id);
        if (rowSet.next()) {
            Meal meal = mapRowToMeal(rowSet);
            result.add(meal);
        }
        return result;
    }

    //TODO listAllMeals()

    //TODO updateMeal()


    private Meal mapRowToMeal(SqlRowSet rowSet) {
        Meal result = new Meal();
        result.setMeal_id(rowSet.getInt("meal_id"));
        result.setMeal_name(rowSet.getString("meal_name"));
        result.setMeal_type_id(rowSet.getInt("meal_type_id"));
        return result;
    }
}





