package com.techelevator.service;

import com.techelevator.model.SpoonacularModels.IngredientAmount;
import com.techelevator.model.SpoonacularModels.IngredientInformation.IngredientInformation;
import com.techelevator.model.SpoonacularModels.IngredientInformation.Nutrient;
import com.techelevator.model.SpoonacularModels.IngredientInformation.NutritionDTO;
import com.techelevator.model.SpoonacularModels.SpoonacularModel;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.techelevator.model.SpoonacularModels.Result;

import java.util.ArrayList;

@Service
public class SpoonacularServiceImpl implements SpoonacularService{
    private static final String BASE_URL = "https://api.apilayer.com/spoonacular/food/ingredients";

    private static final String API_KEY_NAME = "apikey";
    private static final String API_KEY = "x75KbvgKbDpSaHzJoq9Kr8HYTWeiOgux";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public SpoonacularModel getSpoonacularModel(String input){
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY_NAME, API_KEY);
        ResponseEntity<SpoonacularModel> responseEntity = restTemplate.exchange(
                BASE_URL+" /search?sortDirection=asc&sort=calories&query={input}&offset=0&number=10&intolerances=none",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                SpoonacularModel.class,
                input
        );

        SpoonacularModel spoonModel = responseEntity.getBody();
        return spoonModel;
    }

    public Result getIngredientName(){
        Result resultModel = restTemplate.getForObject(BASE_URL+" /search?sortDirection=asc&sort=calories&query=potato&offset=0&number=10&intolerances=none", Result.class);
        ResponseEntity response = restTemplate.getForEntity(BASE_URL+" /search?sortDirection=asc&sort=calories&query=potato&offset=0&number=10&intolerances=none", Result.class);
        String ingredientName = "Hello!";
        return resultModel;
    }

    public Result getResult(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY_NAME, API_KEY);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(
                BASE_URL+" /search?sortDirection=asc&sort=calories&query=potato&offset=0&number=10&intolerances=none",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Result.class
        );
        Result resultModel = responseEntity.getBody();

        //Result resultModel = restTemplate.getForObject(BASE_URL+" /search?sortDirection=asc&sort=calories&query=potato&offset=0&number=10&intolerances=none", Result.class);
       // ResponseEntity response = restTemplate.getForEntity(BASE_URL+" /search?sortDirection=asc&sort=calories&query=potato&offset=0&number=10&intolerances=none", Result.class);
        //String ingredientName = "Hello!";
        return resultModel;
    }

    public IngredientAmount getIngredientAmount(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY_NAME, API_KEY);
        ResponseEntity<IngredientAmount> responseEntity = restTemplate.exchange(
                BASE_URL + "/{id}/amount?unit=oz&target=100&nutrient=calories",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                IngredientAmount.class,
                id
        );
        IngredientAmount ingredientAmount = responseEntity.getBody();
        return ingredientAmount;
    }

    public IngredientInformation getIngredientInformation(int id, String unit, double amount){
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY_NAME, API_KEY);
        ResponseEntity<IngredientInformation> responseEntity = restTemplate.exchange(
                BASE_URL + "/{id}/information?unit={unit}&amount={amount}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                IngredientInformation.class,
                id,
                unit,
                amount
        );
        IngredientInformation ingredientInformation = responseEntity.getBody();
        ArrayList<Nutrient> nutrientList = ingredientInformation.getNutrition().getNutrients();
        NutritionDTO nutritionDTO = new NutritionDTO();
        nutritionDTO.nutritionMap(nutrientList);
        return ingredientInformation;
    }




}
