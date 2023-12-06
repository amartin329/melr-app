package com.techelevator.dao.website;

import com.techelevator.model.website.Meal;
import com.techelevator.model.website.MealPlan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMealPlanDao implements MealPlanDao {

    // createMealPlan()
    // deleteMealPlan()
    // listRecipeByMealId()
    // listAllMealPlans()
    // updateMealPlan()

private JdbcTemplate jdbcTemplate;

public JdbcMealPlanDao(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

//we need a createMeal() method
@Override
    public MealPlan createMealPlan(MealPlan mealPlan) {
    String sql = "INSERT INTO mealplan (mealplan_name, mealplan_type_id) VALUES (?, ?) RETURNING mealplan_id;";
    int newId = jdbcTemplate.queryForObject(sql, int.class, mealPlan.getMealplan_name(), mealPlan.getMealplan_type_id());
    mealPlan.setMealplan_id(newId);

    if(mealPlan.getMeals() != null && mealPlan.getMeals().size() > 0) {
    for (Meal meal : mealPlan.getMeals()) {
        meal = createMeal(mealPlan.getId(), meal);
        }
    }
    return mealPlan;
    }

    @Override
    public boolean deleteMealPlan(int mealplan_id){
    String sql = "DELETE FROM mealplan WHERE mealplan_id = ?;";
    jdbcTemplate.update(sql, mealplan_id);
    sql = "DELETE FROM meal_mealplan WHERE mealplan_id = ?;";
    jdbcTemplate.update(sql, mealplan_id);
    sql = "DELETE FROM mealplan_schedule WHERE mealplan_id = ?;";
    jdbcTemplate.update(sql, mealplan_id);
    int count = jdbcTemplate.update(sql, mealplan_id);
    return count == 1;
    }

    //TODO listMealPlanByTypeId()
    //listMealPlanByTypeId() goes here

    private List<MealPlan> listMealPlanByTypeId(int mealplan_type_id) {
    List<MealPlan> result = new ArrayList<>();
    String sql = "SELECT mealplan_id, mealplan_name, mealplan_type_id FROM mealplan WHERE mealplan_type_id = ?;";
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealplan_type_id);
    if (rowSet.next()) {
        MealPlan mealPlan = mapRowToMealPlan(rowSet);
        result.add(mealPlan);
        }
        return result;

    }

    //TODO listAllMealPlans()


    //TODO updateMealPlan()


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
