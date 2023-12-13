<template>
    <h1>Add New Recipe</h1>
    <!-- <p>Current Recipes:</p>
    <p v-for="userRecipe in recipes" v-bind:key="userRecipe.recipeName">{{ userRecipe.recipeName }}</p> -->
  <form class="recipeForm" @submit.prevent="createRecipe">
    <div class="form-group">
        <label for="recipe-name">Recipe name:</label>
        <input id="recipe-name" v-model="recipe.recipeName" type="text" class="form-control" required/>
    </div>
    <div class="form-group">
        <label for="prep-time">Prep time in minutes:</label>
        <input id="prep-time" type="number" v-model="recipe.prepTime" class="form-control" required/>
    </div>
    <div class="form-group">
        <label for="directions">Directions:</label>
        <textarea id="directions" type="textarea" v-model="recipe.instruction" class="form-control" required> </textarea>
    </div>
    <!-- <IngredientForm v-on:click.prevent/> -->
    <new-ingredient-form v-on:click.prevent></new-ingredient-form>
    <button v-on:click.prevent="testCreateRecipe(recipe)">Create Recipe</button>
  </form>
 

</template>

<script>
import IngredientForm from './IngredientForm.vue';
import NewIngredientForm from './NewIngredientForm.vue';
export default {
  components: { 
  NewIngredientForm },
  data(){
    return{
    
        recipe:{
            recipeName: "",
            instruction: "",
            prepTime: 0

        },
        recipeIngredients:[],
        recipes: []
    }
  },
  methods:{
    testCreateRecipe(recipe){
        this.recipes.push(recipe);
        this.recipe = {};
    }
  }

}
</script>

<style scoped>
.recipeForm {
  padding: 10px;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}

label {
  display: inline-block;
  margin-bottom: 0.5rem;
}

.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}

textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}

select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}

</style>
