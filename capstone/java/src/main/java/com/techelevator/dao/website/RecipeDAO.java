package com.techelevator.dao.website;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Nutrition;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface RecipeDAO {
    //I left 'favorited' out of this, since it seems like that would act funny
    //TODO talk about this
    Recipe createRecipe(Recipe recipe);

    int deleteRecipe(int recipe_id);

    List<Recipe> listAllRecipes();

    Recipe getRecipeDetailsById(int recId);

    List<Ingredient> getIngredientListForRecipe(int recId);

    Nutrition getNutritionForIngredient(int ingId);

    List<Nutrition> getNutritionForRecipe(int recId);

    List<Recipe> listRecipeByMealId(int recipe_id);


}
