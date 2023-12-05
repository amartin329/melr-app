package com.techelevator.service;

import com.techelevator.dao.website.WebsiteDAO;
import com.techelevator.model.website.Ingredient;
import com.techelevator.model.website.Recipe;
import com.techelevator.model.website.Meal;
import com.techelevator.model.website.MealPlan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class WebsiteServiceImpl implements WebsiteService {

    private WebsiteDAO websiteDAO;
}

