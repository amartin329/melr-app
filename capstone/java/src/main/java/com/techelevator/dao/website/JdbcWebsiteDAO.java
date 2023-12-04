package com.techelevator.dao.website;

import com.techelevator.model.website.Ingredient;
import com.techelevator.model.website.Recipe;
import com.techelevator.model.website.Meal;
import com.techelevator.model.website.MealPlan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

public class JdbcWebsiteDAO implements WebsiteDAO {

    private JdbcTemplate jdbcTemplate;

}
