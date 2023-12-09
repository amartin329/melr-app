package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import com.techelevator.service.MealplanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mealplans")
public class MealplanController {
    private MealplanService mealplanService;

    public MealplanController(MealplanService mealplanService) {
        this.mealplanService = mealplanService;
    }


    @GetMapping()
    public List<Mealplan> getMealPlans() throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time
        return mealplanService.getMealplans();
    }

    @GetMapping("/{id}")
    public Mealplan getMealplanById(int id) throws InterruptedException {
        Thread.sleep(1000); //Simulated loading time
        Mealplan result = mealplanService.getMealplanById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Mealplan createMealplan(@Valid @RequestBody Mealplan mealplan) {
        try {
            Mealplan newMealplan = mealplanService.createMealplan(mealplan);
            if (newMealplan == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newMealplan;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }


}
