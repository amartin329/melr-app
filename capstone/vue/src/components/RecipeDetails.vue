<template>
    <h1>{{ this.$store.state.currentRecipe.recipeName}}</h1>
    <h2>Ingredients:</h2>
    <div class="ingredient" v-for="ingredient in this.$store.state.currentRecipe.ingredientList" v-bind:key="ingredient.ingId">
      <!-- <router-link v-bind:key="ingredient.ingId"
      v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}"> -->
          <p>{{ ingredient.ingName }}</p>
          <p>{{ ingredient.quantity }}</p>
          <p>{{ ingredient.msmUnit }}</p>
      <!-- </router-link> -->
    </div>
    <h2>DIRECTIONS:</h2>
    {{ this.$store.state.currentRecipe.instruction }}
    <!-- <addMealToPlanForm v-if="addMealIsVisible" class="popup"></addMealToPlanForm>
    <button v-if="!addMealIsVisible" v-on:click.prevent="toggleAddMeal">Add Meal to Meal Plan</button>
    <button v-if="addMealIsVisible" v-on:click.prevent="toggleAddMeal">Exit without saving</button>
   -->
  </template>
  
  <script>

  
  export default {
      data() {
          return {
              addMealIsVisible: false,
              recipeId: this.$route.params.id
          };
      },
      methods: {
          getCurrentRecipe(recipeId) {
              this.$store.dispatch('getRecipeById', recipeId);
          },
        //   toggleAddMeal() {
        //       this.addMealIsVisible = !this.addMealIsVisible
        //   },
  
      
      },
      created() {
          this.getCurrentRecipe(this.recipeId);
      }
      // props: ['mealPlan']
      ,
    //   components: { addMealToPlanForm }
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