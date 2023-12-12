<template>
    <h1>{{ this.$store.state.currentMeal.mealName}}</h1>
    <div class="recipe" v-for="recipe in this.$store.state.currentMeal.recipeList" v-bind:key="recipe.recipeId">
      <router-link v-bind:key="recipe.recipeId"
      v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}">
          <p>{{ recipe.recipeName }}</p>
      </router-link>
    </div>
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
              mealId: this.$route.params.id
          };
      },
      methods: {
          getCurrentMeal(mealId) {
              this.$store.dispatch('getMealById', mealId);
          },
        //   toggleAddMeal() {
        //       this.addMealIsVisible = !this.addMealIsVisible
        //   },
  
      
      },
      created() {
          console.log(this.mealId);
          this.getCurrentMeal(this.mealId);
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

  .meal-plan{
    font-weight: 800;
    
  }

  .home{
    margin-top: -50px
  }

  .meal-plan > h4{
    font-weight: 800;
    font-family: Georgia, 'Times New Roman', Times, serif;
  }

  .meal-plan > p{
    margin-top: 75px;
  }

  .meal-plans{
    display: flex;
    margin-top: 100px
  }
  </style>