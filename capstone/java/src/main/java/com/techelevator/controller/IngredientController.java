package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Ingredient;
import com.techelevator.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientService ingredientService;
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping()
    public List<Ingredient> getIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        try{
            allIngredients = ingredientService.getIngredients();
            if (allIngredients == null) {
                throw new ServiceException("Recipes not found.");
            } else {
                return allIngredients;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(int id) {
        try{
            Ingredient ingredient = ingredientService.getIngredientById(id); // wait to see the corresponding method in recipeDao
            if (ingredient == null) {
                throw new ServiceException("Recipe id: " + id + " was not found.");
            } else {
                return ingredient;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient) {
        try {
            Ingredient newIngredient = ingredientService.createIngredient(ingredient);
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