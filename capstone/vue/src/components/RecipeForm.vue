<template>
    <h1>My recipes:</h1>
    <p v-for="userRecipe in recipes" v-bind:key="userRecipe.recipeName">{{ userRecipe.recipeName }}</p>
  <form class="recipeForm">
    <div class="form-group">
        <label for="recipe-name">Recipe name:</label>
        <input id="recipe-name" v-model="recipe.recipeName" type="text" />
    </div>
    <div class="form-group">
        <label for="prep-time">Prep time in minutes:</label>
        <input id="prep-time" type="number" v-model="recipe.prepTime"/>
    </div>
    <div class="form-group">
        <label for="directions">Directions:</label>
        <textarea id="directions" type="textarea" v-model="recipe.instruction"> </textarea>
    </div>
    <button v-on:click.prevent="testCreateRecipe(recipe)">Create Recipe</button>
  </form>
  <IngredientForm/>

</template>

<script>
import IngredientForm from './IngredientForm.vue';
export default {
  components: { IngredientForm },
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

<style>
    #directions{
        height: 150px;
        width: 500px;
    }
</style>