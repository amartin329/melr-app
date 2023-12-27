package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.Mealplan;
import com.techelevator.service.MealplanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
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
    public List<Mealplan> getMealPlans(Principal user) throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time
        return mealplanService.getMealplans(user);
    }

    @GetMapping("/{id}")
    public Mealplan getMealplanById(@PathVariable int id, Principal user) throws InterruptedException {
//        Thread.sleep(1000); //Simulated loading time
        Mealplan result = mealplanService.getMealplanById(id, user);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No mealPlan with that id.");
        } else {
            return result;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Mealplan createMealplan(@Valid @RequestBody Mealplan mealplan, Principal user) {
        try {
            Mealplan newMealplan = mealplanService.createMealplan(mealplan, user);
            if (newMealplan == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return newMealplan;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @PutMapping("/{id}")
    public Mealplan updateMealplanInfo(@PathVariable int id, @RequestBody Mealplan mealplan, Principal user) {
            Mealplan newMealplan = mealplanService.updateMealplanInfo(id, mealplan, user);
            if (newMealplan != null) {
                return newMealplan;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mealplan not found to update.");
            }
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{mealplanId}/modify/{mealId}")
    public int addMealToMealplan(@PathVariable int mealplanId, @PathVariable int mealId, Principal user) {
        int rowAffected;
        try {
             rowAffected = mealplanService.addMealToMealplan(mealplanId, mealId, user);
            if (rowAffected == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No row change. Expect 1.");
            } else {
                return rowAffected;
            }
        } catch(ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    @DeleteMapping("/{mealplanId}/modify/{mealId}")
    public int removeMealFromMealplan(@PathVariable int mealplanId, @PathVariable int mealId, Principal user) {
        int rowAffected;
        try {
            rowAffected = mealplanService.removeMealFromMealplan(mealplanId, mealId, user);
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
