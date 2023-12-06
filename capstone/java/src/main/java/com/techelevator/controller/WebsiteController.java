package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Recipe;
import com.techelevator.model.website.Ingredient;
import com.techelevator.model.website.Meal;
import com.techelevator.model.website.MealPlan;
import com.techelevator.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class WebsiteController {

    private WebsiteService websiteService;
    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/mealplans")
    public List<MealPlan> getMealPlans() throws InterruptedException {
        Thread.sleep(2000); //Simulated loading time

        return websiteService.getMealPlans(); // wait to see the corresponding method in mealPlanDao
    }

    @GetMapping("/mealplans/{id}")
    public MealPlan getMealPlan(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time

        MealPlan result = websiteService.getMealPlan(id); // wait to see the corresponding method in mealPlanDao
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mealplans")
    public MealPlan createMealPlan(@Valid @RequestBody MealPlan mealPlan) { //Creates a Send Transfer
        try {
            MealPlan newMealPlan = websiteService.createMealPlan(mealPlan);
            if (newMealPlan == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newMealPlan;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }


    @PutMapping("/mealplans/modify")
    public MealPlan modifyMealPlan(@Valid @RequestBody MealPlan mealPlan) {
        try {
            MealPlan modifiedMealPlan = websiteService.updateMealPlan(mealPlan);
            if (modifiedMealPlan == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return modifiedMealPlan;
            }
        }catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        List<Recipe> allRecipes = new ArrayList<>();
        try{
            allRecipes = websiteService.getRecipes(); // wait to see the corresponding method in recipe Dao
            if (allRecipes == null) {
                throw new ServiceException("Recipes not found.");
            } else {
                return allRecipes;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(int id) {
        try{
            Recipe recipe = websiteService.getRecipeById(id); // wait to see the corresponding method in recipeDao
            if (recipe == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return recipe;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/recipes")
    public Recipe addRecipe(@Valid @RequestBody Recipe recipe) { //Creates a Send Transfer
        try {
            Recipe newRecipe = websiteService.addRecipe(recipe);
            if (newRecipe == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newRecipe;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @PutMapping("/recipes/modify")
    public Recipe modifyRecipe(@Valid @RequestBody Recipe recipe) {
        try {
            Recipe modifiedRecipe = websiteService.modifyRecipe(recipe);
            if (modifiedRecipe == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return modifiedRecipe;
            }
        }catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/ingredients")
    public Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient) { //Creates a Send Transfer
        try {
            Ingredient newIngredient = websiteService.addIngredient(ingredient);
            if (newIngredient == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newIngredient;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }




}
