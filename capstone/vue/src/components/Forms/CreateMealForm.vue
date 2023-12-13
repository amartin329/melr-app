<template>

  <form class="plan-form" @submit.prevent="createMeal">
    <div class="form-group">
        <label for="plan-name">Meal Name:</label>
        <input id="plan-name" v-model="newMeal.mealName" type="text" required
        class="form-control" placeholder="Meal Name"/>
    </div>
    <h3>Recipes</h3>
    <div class="pending-recipes" v-for="pendingRecipe in pendingRecipes" v-bind:key="pendingRecipe.recipeId">
        <h2 >{{ pendingRecipe.recipeName }}</h2> <button class="remove" v-on:click.prevent="removeFromPending(pendingRecipe.recipeId)">X</button>
    </div>
   

    <div class="form-group">
        <label for="recipe-filter">Add a recipe:</label>
        <input id="recipe-filter" type="text" autocomplete="off"
        v-model="this.filter.recipeName" class="form-control"
        placeholder="Enter a recipe:"/>
        <div v-if="filteredRecipes.length > 0" class="autocomplete-dropdown">
            <div v-for="recipe in this.filteredRecipes" v-bind:key="recipe.recipeId"
            @click.prevent="addRecipeToPending(recipe)">
                {{ recipe.recipeName}}
            </div>
        </div>
    </div>
    <button type="submit" class="submit-form">CREATE MEAL</button>
  </form>
 

</template>

<script>
import mealService from '../../services/MealService';
export default {
  data(){
    return{
        filter:{
            recipeName: "",
            recipeTypeId: "",
            recipeId: ""

        },
        newMeal:{
            mealName: "",
            mealTypeId: 0,
        },
        recipes: [...this.$store.state.recipes],
        pendingRecipes: [],

    }
  },
  methods:{
    testCreateRecipe(recipe){
        this.recipes.push(recipe);
        this.recipe = {};
    },

    addRecipeToPending(recipe){
        this.pendingRecipes.push(recipe);
    },

    createMeal(){
        if(this.pendingRecipes.length == 0){
            return;
        }
        //first creates meal (ie | recipe container)
        this.$store.dispatch('createMeal', this.newMeal).then(response => {
      console.log("RESPONSE = " + response) 
            
          //then, we add each pending recipe to the meal
                console.log("MEAL ID " + response.mealId)
                this.pendingRecipes.forEach(pendingRecipe => {
                    console.log(pendingRecipe)
                    let pendingRecipeId = pendingRecipe.recipeId;
                    this.$store.dispatch('addRecipeToMeal', {mealId: response.mealId, recipeId: pendingRecipeId})
})
            
            this.$router.push('/meals');
        })
       
    },
    removeFromPending(id){

            this.pendingRecipes = this.pendingRecipes.filter(pendRecipe => {
                return pendRecipe.recipeId !== id;
            });
        
    }
  },
  computed: {
    filteredRecipes(){
        console.log('Filter:', this.filteredRecipes);

        let filteredRecipes = [...this.recipes];
  
            filteredRecipes = filteredRecipes.filter((recipe) => {
                if(!this.pendingRecipes.includes(recipe)){
                    return recipe.recipeName.toLowerCase()
                    .includes(this.filter.recipeName.toLowerCase())
                }
            
            });
            
        
        return filteredRecipes;
    }
  },
  created(){
    this.$store.dispatch('getRecipes');
    console.log('Create!', this.meals);
    console.log('STORE!'), this.$store.state.meals;

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
  margin: auto;
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

.autocomplete-dropdown {
  border: 1px solid black;
  width: 80%;
  margin:auto;
  max-height: 150px;
  overflow-y: auto;
  background-color: white;
}
.autocomplete-dropdown div {
  padding: 5px;
  cursor: pointer;
}

.autocomplete-dropdown div:hover{
    background-color: lightgray;
}

.submit-form{
    background-color: orange;
    width: 80%;
    height: 10vh;
    font-size: 1.5em;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.submit-form:hover{
    background-color: rgb(91,206,250)
}

.pending-recipes{
    display:flex;
    justify-content: center;
    align-items: center;
}

.pending-recipes > button{
    margin-left: 10px;
}

</style>
