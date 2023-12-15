<template>
    <h1>{{ this.$store.state.currentMeal.mealName}}</h1>

    <div class="recipe" v-for="recipe in this.$store.state.currentMeal.recipeList" v-bind:key="recipe.recipeId">
      <router-link v-if="!addRecipeIsVisible" v-bind:key="recipe.recipeId"
      v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}">
          <p>{{ recipe.recipeName }}</p>
      </router-link>
      <div class="recipe-delete" v-else>
        <p>{{ recipe.recipeName }}</p>
        <button v-on:click.prevent="removeRecipeFromMeal(this.mealId, recipe.recipeId)">Remove Recipe</button>
      </div>
    </div>
    <add-recipe-to-meal-form v-if="addRecipeIsVisible" class="popup"></add-recipe-to-meal-form>
    <button v-if="!addRecipeIsVisible" v-on:click.prevent="toggleEdit">Edit Meal</button>
    <button v-if="addRecipeIsVisible" v-on:click.prevent="toggleEdit">Cancel</button>

  </template>
  
  <script>
import AddRecipeToMealForm from './Forms/AddRecipeToMealForm.vue';

  
  export default {
  components: { AddRecipeToMealForm },
      data() {
          return {
              addRecipeIsVisible: false,
              mealId: this.$route.params.id
          };
      },
      methods: {
          getCurrentMeal(mealId) {
              this.$store.dispatch('getMealById', mealId);
          },
          toggleEdit(){
            this.addRecipeIsVisible = !this.addRecipeIsVisible;
          },

          removeRecipeFromMeal(mealId, recipeId){
            if(confirm("Are you sure you want to remove this recipe?")){

            
            this.$store.dispatch('removeRecipeFromMeal', {mealId: mealId, recipeId: recipeId}).then(response =>{
              window.alert("Successfully removed recipe.")
            this.toggleEdit();
            this.getCurrentMeal(mealId);
            });
          }
          }
        //   toggleAddMeal() {
        //       this.addMealIsVisible = !this.addMealIsVisible
        //   },
  
      
      },
      created() {
          console.log(this.mealId);
          this.getCurrentMeal(this.mealId);
          this.addRecipeIsVisible = false;
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