<template>
      <h1>{{ this.$store.state.user.username }}'s Meal Plans</h1>
      <div class="d-grid gap-2">
        <router-link class ="btn btn-primary" v-bind:to="{name: 'create-plan'}">Create New Meal Plan</router-link>

      </div>
      <div class="plan-list">
        <div id="mealplanDetail" v-for="mealplan in mealplans" v-bind:key="mealplan.id" class="plan-container">
          <meal-plan-card v-bind:mealplan="mealplan" />
        </div>  
      </div>
     
</template>

<script>
import MealPlanCard from '../../components/MealPlanCard.vue'
import MealPlanList from '../../components/MealPlanList.vue'
import MealPlanService from '../../services/MealPlanService'

export default {
  components: { MealPlanCard },

  data() {
    return {
      mealplans:[]
    }
  },

  created() {
    MealPlanService.getMealPlans().then( response => {
      this.mealplans = response.data;
    })
  },
    
}
</script>


<style>
.plan-container{
  width: 48%;
  margin-bottom: 10px;
 /* margin-bottom: 10px; */
 /* display:flex;
 flex-direction: column; */

 
}

.plan-list{
  display: flex;
  flex-wrap:wrap;
  justify-content: space-between;
  
 
}

.btn-primary{
  color: black;
  font-size: 1.2em;
  margin-bottom: 25px;
  background-color: #95E0B7;
  border: 1px solid black;
}
</style>