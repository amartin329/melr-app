package com.techelevator.service;
import com.techelevator.model.SpoonacularModels.IngredientAmount;
import com.techelevator.model.SpoonacularModels.IngredientInformation.IngredientInformation;
import com.techelevator.model.SpoonacularModels.SpoonacularModel;
import com.techelevator.model.SpoonacularModels.Result;
import org.springframework.http.ResponseEntity;


public interface SpoonacularService {
    SpoonacularModel getSpoonacularModel(String input);
   Result getIngredientName();

   Result getResult();

   IngredientAmount getIngredientAmount(int id);

   IngredientInformation getIngredientInformation(int id, String unit, double amount);
}


