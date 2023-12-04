package com.techelevator.controller;

import com.techelevator.model.website.Ingredient;
import com.techelevator.model.website.Recipe;
import com.techelevator.model.website.Meal;
import com.techelevator.model.website.MealPlan;
import com.techelevator.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



public class WebsiteController {

    private WebsiteService websiteService;
}
