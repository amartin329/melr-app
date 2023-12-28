package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import com.techelevator.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
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
    public List<Ingredient> getIngredients(Principal user) {
        List<Ingredient> allIngredients = new ArrayList<>();
        try{
            allIngredients = ingredientService.getIngredients(user);
            if (allIngredients == null) {
                throw new ServiceException("Ingredients not found.");
            } else {
                return allIngredients;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable int id, Principal user) {
        try{
            Ingredient ingredient = ingredientService.getIngredientById(id, user);
            if (ingredient == null) {
                throw new ServiceException("Ingredient id: " + id + " was not found.");
            } else {
                return ingredient;
            }
        }catch(DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient, Principal user) {
        try {
            Ingredient newIngredient = ingredientService.createIngredient(ingredient, user);
            if (newIngredient == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newIngredient;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable int id, @RequestBody Ingredient updatedIngredient) {
            Ingredient newIngredient = ingredientService.updateIngredient(id, updatedIngredient);
            if (newIngredient != null) {
                return newIngredient;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found to update.");
            }
    }

}
