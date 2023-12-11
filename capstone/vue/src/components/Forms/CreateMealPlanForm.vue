<template>

  <form class="plan-form">
    <div class="form-group">
        <label for="plan-name">Meal Plan Name:</label>
        <input id="plan-name" v-model="newMealPlan.mealplanName" type="text" 
        class="form-control" placeholder="Meal Plan Name"/>
    </div>
    <h3>Meals:</h3>
    <h2 v-for="pendingMeal in pendingMeals" v-bind:key="pendingMeal.mealId">{{ pendingMeal.mealName }}</h2>
    <div class="form-group">
        <label for="meal-filter">Add a meal:</label>
        <input id="meal-filter" type="text" 
        v-model="this.filter.mealName" class="form-control"
        placeholder="Enter a meal:"/>
        <div v-if="filteredMeals.length > 0" class="autocomplete-dropdown">
            <div v-for="meal in this.filteredMeals" v-bind:key="meal.mealId"
            @click.prevent="addMealToPending(meal)">
                {{ meal.mealName }}
            </div>
        </div>
    </div>
    <button class="submit-form" v-on:click.prevent="createMealPlan">CREATE MEAL PLAN</button>
  </form>
 

</template>

<script>
import mealPlanService from '../../services/MealPlanService';
export default {
  data(){
    return{
        filter:{
            mealName: "",
            mealTypeId: "",
            mealId: ""

        },
        newMealPlan:{
            mealplanName: "",
            mealplanTypeId: 0,
        },
        meals: [...this.$store.state.meals],
        pendingMeals: [],

    }
  },
  methods:{
    testCreateRecipe(recipe){
        this.recipes.push(recipe);
        this.recipe = {};
    },
    getMeals(){
        this.$store.dispatch('getMeals');
        this.meals = [...this.$store.state.meals];
    },
    addMealToPending(meal){
        this.pendingMeals.push(meal);
        console.log(this.pendingMeals)
        console.log('Are ya seeing pendingMeals?')
    },
    // NOTE: SHOULD PROBABLY LOOP ON SERVER-SIDE TO MAKE THIS A TRANSACTION.
    createMealPlan(){
        let responsePlan = this.newMealPlan;
        this.$store.dispatch('createMealPlan', this.newMealPlan).then(response => {
      
            
                
                console.log("MEAL PLAN ID: " + responsePlan)
                this.pendingMeals.forEach(pendingMeal => {
                    console.log(pendingMeal)
                    let pendingMealId = pendingMeal.mealId;
                    this.$store.dispatch('addMealToPlan', {mealplanId: responsePlan.mealplanId, mealId: pendingMealId})
})
            
            this.$router.push('/plans');
        })
       
    }
  },
  computed: {
    filteredMeals(){
        console.log('Filter:', this.filteredMeals);

        let filteredMeals = [...this.meals];
        if(this.filter.mealName != ""){
            filteredMeals = filteredMeals.filter((meal) => {
               return meal.mealName.toLowerCase()
                .includes(this.filter.mealName.toLowerCase())
            });
            
        }
        return filteredMeals;
    }
  },
  created(){
    this.$store.dispatch('getMeals');
    this.getMeals();
    console.log('Create!', this.meals);
    console.log('STORE!'), this.$store.state.meals;

  },
  watch: {
    pendingMeals(newVal, oldVal){
        this.getMeals();
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

</style>
