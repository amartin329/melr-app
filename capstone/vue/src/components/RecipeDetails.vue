<template>
    <h1>{{ this.$store.state.currentRecipe.recipeName}}</h1>
    <h2>PREP TIME: {{ this.$store.state.currentRecipe.prepTime }} minutes</h2>
    <h2>Ingredients:</h2>
    <div class="ingredient" v-for="ingredient in this.$store.state.currentRecipe.ingredientList" v-bind:key="ingredient.ingId">
      <!-- <router-link v-bind:key="ingredient.ingId"
      v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}"> -->
          <p>{{ ingredient.quantity }} {{ ingredient.msmUnit }} {{ ingredient.ingName }}</p>
          <button v-if="apiFormIsVisible" v-on:click.prevent="removeIngredientFromRecipe(this.recipeId, ingredient.ingId)">Remove Ingredient</button>
          <p></p>
          <p></p>
      <!-- </router-link> -->
    </div>
    <h2>DIRECTIONS:</h2>
    <p>{{ this.$store.state.currentRecipe.instruction }}</p>

    <new-ingredient-form v-if="apiFormIsVisible"></new-ingredient-form>
    <button v-if="!apiFormIsVisible" v-on:click.prevent="toggleApiForm">Add/Remove Ingredients</button>
    <button v-if="apiFormIsVisible" v-on:click.prevent="toggleApiForm">Cancel</button>
    <!-- <button v-if="!ingredientFormIsVisible" v-on:click.prevent="toggleIngredientForm">Create New Ingredient</button> -->
    <create-ingredient-form v-if="ingredientFormIsVisible"></create-ingredient-form>
    <!-- <addMealToPlanForm v-if="addMealIsVisible" class="popup"></addMealToPlanForm>
    <button v-if="!addMealIsVisible" v-on:click.prevent="toggleAddMeal">Add Meal to Meal Plan</button>
    <button v-if="addMealIsVisible" v-on:click.prevent="toggleAddMeal">Exit without saving</button>
   -->
  </template>
  
  <script>
import NewIngredientForm from './Forms/NewIngredientForm.vue';
import CreateIngredientForm from './Forms/CreateIngredientForm.vue';

  
  export default {
    components:{
        NewIngredientForm,
        CreateIngredientForm
    },
      data() {
          return {
        
                ingredientFormIsVisible: false,
              recipeId: this.$route.params.id,
              apiFormIsVisible: false,
            
          };
      },
      methods: {
          getCurrentRecipe(recipeId) {
              this.$store.dispatch('getRecipeById', recipeId);
          },
          toggleApiForm(){
            this.apiFormIsVisible = !this.apiFormIsVisible;
          },
          toggleIngredientForm(){
            this.ingredientFormIsVisible = !this.ingredientFormIsVisible
          },
          removeIngredientFromRecipe(recipeId, ingId){
            this.$store.dispatch('removeIngredientFromRecipe', {recipeId: recipeId, ingId: ingId})
            this.toggleApiForm();
            this.getCurrentRecipe(recipeId);
          },
        //   toggleAddMeal() {
        //       this.addMealIsVisible = !this.addMealIsVisible
        //   },
  
      
      },
      created() {
          this.getCurrentRecipe(this.recipeId);
          this.apiFormIsVisible = false;
      }

  }
  </script>
  
  <style>
  .popup{
      width:500px;
   height:500px;
   margin:0 auto;
   background:#f7f7f7;
   position:absolute;
   left:50%;
   top:50%;
   margin-left:-250px;
   margin-top:-250px;
   border: 5px solid black;
   border-radius: 5px;
  }

  p {
    color:black;
  }
  </style>