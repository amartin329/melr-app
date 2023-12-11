package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import com.techelevator.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Provider;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/meals")
public class MealController {
    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @GetMapping()
    public List<Meal> getMeals() throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time
        return mealService.getMeals();
    }

    @GetMapping("/{id}")
    public Meal getMealById(@PathVariable int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time
        Meal result = mealService.getMealById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Meal createMeal(@Valid @RequestBody Meal meal) {
        try {
            Meal newMeal = mealService.createMeal(meal);
            if (newMeal == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newMeal;
            }
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @PutMapping("/{id}")
    public Meal updateMealInfo(@PathVariable int id, @RequestBody Meal meal) {
        Meal newMeal = mealService.updateMealInfo(id, meal);
        if (newMeal != null) {
            return newMeal;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Meal not found to update.");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{mealId}/modify/{recipeId}")
    public int addRecipeToMeal(@PathVariable int mealId, @PathVariable int recipeId) {
        int rowAffected;
        try {
            rowAffected = mealService.addRecipeToMeal(mealId, recipeId);
            if (rowAffected == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No rows affected, expected at least 1.");
            } else {
                return rowAffected;
            }

        } catch (
                ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }


        @DeleteMapping("/{mealId}/modify/{recipeId}")
        public int removeRecipeFromMeal(@PathVariable int mealId, @PathVariable int recipeId){
            int rowAffected;
            try {
                rowAffected = mealService.removeRecipeFromMeal(mealId, recipeId);
                if (rowAffected == 0) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No row change. Expect 1.");
                } else {
                    return rowAffected;
                }
            } catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            }
        }
    }