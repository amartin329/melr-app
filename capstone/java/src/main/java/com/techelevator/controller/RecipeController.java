package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import com.techelevator.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.ConstructorParameters;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping()
    public List<Recipe> getRecipes(Principal user) {
        List<Recipe> allRecipes = new ArrayList<>();
        try{
            allRecipes = recipeService.getRecipes(user);
            if (allRecipes == null) {
                throw new ServiceException("Recipes not found.");
            } else {
                return allRecipes;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable int id, Principal user) {
        try{
            Recipe recipe = recipeService.getRecipeById(id, user);
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
    @PostMapping()
    public Recipe createRecipe(@Valid @RequestBody Recipe recipe, Principal user) {
        try {
            Recipe newRecipe = recipeService.createRecipe(recipe, user);
            if (newRecipe == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Controller error: newRecipe == null");
            } else {
                return newRecipe;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Misc Controller error.");
        }
    }


    @PutMapping("/{id}")
    public Recipe updateRecipeInfo(@PathVariable int id, @RequestBody Recipe updatedRecipe, Principal user) {
            Recipe newRecipe = recipeService.updateRecipeInfo(id, updatedRecipe, user);
            if (newRecipe != null) {
                return newRecipe;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found to update.");
            }
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recipeId}/modify/{ingId}") // need to add these in Postman ?msmId={msmId}&quantity={quantity} to make it work
    //@ResponseBody
    public int addIngredientToRecipe(@PathVariable int recipeId, @PathVariable int ingId, @RequestParam("msmId") int msmId, @RequestParam("quantity") double quantity, Principal user) {
        int rowAffected;
        try {
            rowAffected = recipeService.addIngredientToRecipe(recipeId, ingId, msmId, quantity, user);
            if (rowAffected == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No row change. Expect 1.");
            } else {
                return rowAffected;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @DeleteMapping("/{recipeId}/modify/{ingId}")
    public int removeIngredientFromRecipe(@PathVariable int recipeId, @PathVariable int ingId, Principal user) {
        int rowAffected;
        try {
            rowAffected = recipeService.removeIngredientFromRecipe(recipeId, ingId, user);
            if (rowAffected == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No row change. Expect 1.");
            } else {
                return rowAffected;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }


}
