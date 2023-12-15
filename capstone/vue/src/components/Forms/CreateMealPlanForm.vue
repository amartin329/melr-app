<template>

  <form class="plan-form" @submit.prevent="createMealPlan">
    <div class="form-group">
        <label for="plan-name">Meal Plan Name:</label>
        <input id="plan-name" v-model="newMealPlan.mealplanName" type="text" required
        class="form-control" placeholder="Meal Plan Name"/>
    </div>
    <h3>Meals:</h3>
    <div class="pending-meals" v-for="pendingMeal in pendingMeals" v-bind:key="pendingMeal.mealId">
        <h2 >{{ pendingMeal.mealName }}</h2> <button class="remove" v-on:click.prevent="removeFromPending(pendingMeal.mealId)">X</button>
    </div>
   
    <select id="meal-dropdown"
    v-model="this.chosenMeal">
    <option>--------</option>
    <option v-for="meal in this.$store.state.meals" v-bind:key="meal.mealId"
    :value="meal">
        {{meal.mealName}}
    </option>
  </select>
  <button v-on:click.prevent="addMealToPending(this.chosenMeal)">Add Meal</button>

  <!--TODO: FIX FILTER-->
    <!-- <div class="form-group">
        <label for="meal-filter">Add a meal:</label>
        <input id="meal-filter" type="text" autocomplete="off"
        v-model="this.filter.mealName" class="form-control"
        placeholder="Enter a meal:"/>
        <div v-if="filteredMeals.length > 0" class="autocomplete-dropdown">
            <div v-for="meal in this.filteredMeals" v-bind:key="meal.mealId"
            @click.prevent="addMealToPending(meal)">
                {{ meal.mealName }}
            </div>
        </div>
    </div> -->


    <button type="submit" class="submit-form">CREATE MEAL PLAN</button>
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
        chosenMeal:{}
    }
  },
  methods:{
    testCreateRecipe(recipe){
        this.recipes.push(recipe);
        this.recipe = {};
    },

    addMealToPending(meal){
        this.pendingMeals.push(meal);
        console.log(this.pendingMeals)
        console.log('Are ya seeing pendingMeals?')
    },
    // NOTE: SHOULD PROBABLY LOOP ON SERVER-SIDE TO MAKE THIS A TRANSACTION.
    createMealPlan(){
        if(this.pendingMeals.length == 0){
            return;
        }
        this.$store.dispatch('createMealPlan', this.newMealPlan).then(response => {
      console.log("RESPONSE = " + response) 
            
                
                console.log("MEAL PLAN ID: " + response.mealplanId)
                this.pendingMeals.forEach(pendingMeal => {
                    console.log(pendingMeal)
                    let pendingMealId = pendingMeal.mealId;
                    this.$store.dispatch('addMealToPlan', {mealplanId: response.mealplanId, mealId: pendingMealId})
})
            
            this.$router.push('/plans');
        })
       
    },
    removeFromPending(id){

            this.pendingMeals = this.pendingMeals.filter(pendMeal => {
                return pendMeal.mealId !== id;
            });
        
    }
  },
  computed: {
    filteredMeals(){
        console.log('Filter:', this.filteredMeals);

        let filteredMeals = [...this.meals];
  
            filteredMeals = filteredMeals.filter((meal) => {
                if(!this.pendingMeals.includes(meal)){
                    return meal.mealName.toLowerCase()
                .includes(this.filter.mealName.toLowerCase())
                }
            
            });
            
        
        return filteredMeals;
    }
  },
  created(){
    this.$store.dispatch('getMeals');
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

.pending-meals{
    display:flex;
    justify-content: center;
    align-items: center;
}

.pending-meals > button{
    margin-left: 10px;
}

</style>
