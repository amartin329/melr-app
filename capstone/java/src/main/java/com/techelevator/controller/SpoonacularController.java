package com.techelevator.controller;


import com.techelevator.exception.DaoException;
import com.techelevator.model.SpoonacularModels.IngredientAmount;
import com.techelevator.model.SpoonacularModels.IngredientInformation.IngredientInformation;
import com.techelevator.model.SpoonacularModels.Result;
import com.techelevator.model.SpoonacularModels.SpoonacularModel;
import com.techelevator.service.SpoonacularService;
import com.techelevator.service.SpoonacularServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class SpoonacularController {

    //TODO: Implement user's ingredient database search (here, or other controller?)
    private SpoonacularService spoonacularService;

    public SpoonacularController(SpoonacularService spoonacularService){
        this.spoonacularService = spoonacularService;
    }

    /*TODO: Determine usefulness of following potential user input fields:
        *intolerances [input is comma-separated list. filters out ingredients associated w/ any entered intolerances.]
        * number [number of returned results, between 1 and 100. Currently defaults to 10]
        * offset [number of results to skip. not sure what the point of this is. Currently defaults to 0]
        * sort [many sort options found at https://spoonacular.com/food-api/docs#Recipe-Sorting-Options | currently defaults to calories]
        * sortDirection [asc, desc | currently defaults to asc]
        * metaInformation [THIS RETURNS POSSIBLE UNITS - MAY BE BETTER TO DO HERE THAN IN INGREDIENTINFORMATION. Currently defaults to false]
        * Min/MaxPercent [variety of filters by min or max allowed fat, carbs, protein. Currently unused]
    */

    @GetMapping("/ingredients/search/{input}")
    public ArrayList<Result> searchResults(@PathVariable String input){
        SpoonacularModel spoonModel = spoonacularService.getSpoonacularModel(input);
        ArrayList<Result> results = spoonModel.getResults();
        return results;
    }

    /*TODO: Determine usefulness of following potential user input fields:
            *nutrient [nutrient being targeted. currently defaults to calories. see area below fields for explanation.]
            * target [target number of given nutrient. currently defaults to 100.]
            * unit [target unit. currently defaults to oz.]
        * Taken together, this tells us how much of the given food is required to meet the target # of specified nutrient.
            * For example, if user wants to know how many ounces[unit] of pineapple[id] it takes to reach 100[target] calories[nutrient],
            * the API would return {amount: 7.05, unit: 'oz'}
        * NOTE: This seems backwards for what we need - We want a user to be able to specify the amount of an ingredient required for a recipe,
        * and THEN see the associated nutritional information.
        * Is this endpoint even useful? getIngredientInformation seems to handle what we need.
     */
    @RequestMapping(path = "/ingredients/{id}/amount", method = RequestMethod.GET)
    public IngredientAmount getIngredientAmount(@PathVariable int id){
        IngredientAmount ingredientAmount = spoonacularService.getIngredientAmount(id);
        return ingredientAmount;
    }


/*
    TODO: Clean up endpoint path
 */
    @GetMapping("/ingredients/{id}/information/{unit}/{amount}")
    public IngredientInformation getIngredientInformation(@PathVariable int id, @PathVariable String unit, @PathVariable double amount){
        IngredientInformation ingredientInformation = spoonacularService.getIngredientInformation(id, unit, amount);
        return ingredientInformation;
    }

    /*TODO: Potential other API endpoints
        *Convert amounts https://spoonacular.com/food-api/docs#Convert-Amounts
        *
     */



//    @GetMapping("/api/get-spoon-model")
//    public SpoonacularModel getSpoonacularModel(){
//        SpoonacularModel spoonModel = spoonacularService.getSpoonacularModel();
//        return spoonModel;
//    }

//    @GetMapping("/api/get-ingredient-names")
//    public ArrayList<String> getIngredientNames(){
//        SpoonacularModel spoonModel = spoonacularService.getSpoonacularModel();
//        ArrayList<Result> results = spoonModel.getResults();
//        ArrayList<String> ingredientNames = new ArrayList<>();
//       for(Result result : results) {
//            ingredientNames.add(result.getName());
//        }
//       // Result firstResult = results.get(0);
//        return ingredientNames;
//    }


    @GetMapping("/api/get-results")
    public ArrayList<Result> getResults(){
        SpoonacularModel spoonModel = spoonacularService.getSpoonacularModel("potato");
        ArrayList<Result> results = spoonModel.getResults();
        return results;
    }

//    @GetMapping("/api/get-result")
//    public Result getResult(){
//        Result result = spoonacularService.getIngredientName();
//        return result;
//    }


//    @GetMapping("/test")
//    public Result testResult(){
//        try{
//            Result testResult = new Result(500, "path", "name");
//            return testResult;
//        }catch(DaoException e){
//            throw new DaoException("OOPS!");
//        }
//
//    }

}
