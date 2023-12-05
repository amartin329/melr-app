//package com.techelevator.dao.website;
//
//import com.techelevator.model.website.Meal;
//import com.techelevator.model.website.MealPlan;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//public class JdbcMealPlanDAO implements MealPlanDAO {
//
//    // createMealPlan()
//    // deleteMealPlan()
//    // listRecipeByMealId()
//
//private JdbcTemplate jdbcTemplate;
//
//public JdbcMealPlanDAO(JdbcTemplate jdbcTemplate){
//    this.jdbcTemplate = jdbcTemplate;
//}
//
////we need a createMeal() method
//@Override
//
//    public MealPlan createMealPlan(MealPlan mealPlan) {
//    String sql = "INSERT INTO mealplan (mealplan_name, mealplan_type_id) VALUES (?, ?) RETURNING mealplan_id;";
//    int newId = jdbcTemplate.queryForObject(sql, int.class, mealPlan.getMealplan_name(), mealPlan.getMealplan_type_id());
//    mealPlan.setMealplan_id(newId);
//
//    if(mealPlan.getMeals() != null && mealPlan.getMeals().size() > 0) {
//    for (Meal meal : mealPlan.getMeals()) {
//        meal = createMeal(mealPlan.getId(), meal);
//        }
//    }
//    return mealPlan;
//    }
//
//    @Override
//    public boolean deleteMealPlan(int mealplan_id){
//    String sql = "DELETE FROM mealplan WHERE mealplan_id = ?;";
//    jdbcTemplate.update(sql, mealplan_id);
//    sql = "DELETE FROM meal_mealplan WHERE mealplan_id = ?;";
//    jdbcTemplate.update(sql, mealplan_id);
//    sql = "DELETE FROM mealplan_schedule WHERE mealplan_id = ?;";
//    jdbcTemplate.update(sql, mealplan_id);
//    int count = jdbcTemplate.update(sql, mealplan_id);
//    return count == 1;
//    }
//
//    //TODO listMealPlanById()
//    //listMealPlanById() goes here
//
//    //TODO mapMealToMealPlan()
//    //mapMealToMealPlan() goes here
//
//}
