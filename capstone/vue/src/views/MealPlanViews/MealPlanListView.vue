<template>
    <div id="mealplanDetail" v-for="mealplan in mealplans" v-bind:key="mealplan.id">
      <router-link v-bind:key="mealplan.id" v-bind:to="{name: 'plan-details', params: {id: mealplan.mealplanId}}">
        <meal-plan-card v-bind:mealplan="mealplan" />
      </router-link>
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

</style>