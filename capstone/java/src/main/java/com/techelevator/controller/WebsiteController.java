package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import com.techelevator.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    public List<Mealplan> getMealPlans() throws InterruptedException {
        Thread.sleep(2000); //Simulated loading time

        return websiteService.getMealplans(); // wait to see the corresponding method in mealPlanDao
    }

    @GetMapping("/mealplans/{id}")
    public Mealplan getMealPlan(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time

        Mealplan result = websiteService.getMealplan(id); // wait to see the corresponding method in mealplanDao
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mealplans")
    public Mealplan createMealPlan(@Valid @RequestBody Mealplan mealPlan) {
        try {
            Mealplan newMealplan = websiteService.createMealplan(mealPlan);
            if (newMealplan == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newMealplan;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }


    @PutMapping("/mealplans/modify")
    public Mealplan modifyMealplan(int mealplanId, int mealId) {
        try {
            Mealplan modifiedMealplan = websiteService.modifyMealplan(mealplanId, mealId);
            if (modifiedMealplan == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return modifiedMealplan;
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
            Recipe modifiedRecipe = websiteService.modifyRecipeInfo(recipe);
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
    public Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient) {
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

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        try{
            allIngredients = websiteService.getIngredients(); // wait to see the corresponding method in ingredient Dao
            if (allIngredients == null) {
                throw new ServiceException("Recipes not found.");
            } else {
                return allIngredients;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredientById(int id) {
        try{
            Ingredient ingredient = websiteService.getIngredientById(id); // wait to see the corresponding method in recipeDao
            if (ingredient == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return ingredient;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }




}
