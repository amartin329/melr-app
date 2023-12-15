<template>
  <h2>Add Recipe to {{ this.$store.state.currentMeal.mealName }}</h2>
  <!-- Populate list of user's meal plans -->
  <!-- For each, have checkbox that allows a user to add it to plan -->
  <form>
    <select id="recipe-dropdown"
    v-model="this.addedRecipe">
    <option>--------</option>
    <option v-for="recipe in this.$store.state.recipes" v-bind:key="recipe.recipeId"
    :value="recipe">
        {{recipe.recipeName}}
    </option>
  </select>
  <button v-on:click.prevent="addRecipeToMeal(this.mealId, this.addedRecipe.recipeId)">Add Recipe</button>
  </form>
  

  
</template>

<script>
export default {
    data(){
        return{
            formIsVisible: true,
            mealId: this.$route.params.id,
            addedRecipe: {}
        }
    },


    methods:{
        getRecipes(){
            this.$store.dispatch('getRecipes')
        },

        getCurrentMeal(mealId){
          this.$store.dispatch('getMealById', mealId)
        },

        addRecipeToMeal(mealId, recipeId){
            console.warn("CHECK OUT THESE PARAMETERS!" + mealId + " " + recipeId);
            this.$store.dispatch('addRecipeToMeal', {mealId: mealId, recipeId: recipeId})
            window.alert("Successfully added recipe.")

            this.getCurrentMeal(mealId);
            this.formIsVisible = !this.formIsVisible      
        },

    },
    created(){
        this.getCurrentMeal(this.mealId);
        this.getRecipes();
    },

    computed(){
       
    }
}
</script>

<style>
.form {
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