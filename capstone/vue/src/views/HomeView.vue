<template>
  <div class="home">
    <h1>Welcome Home!</h1>
    <meal-plan-list v-bind:plans="plans" />
  </div>
</template>

<script>
import MealPlanList from '../components/MealPlanList.vue'
export default{
  components: { MealPlanList },
  data(){
    return{
      plans: [
        {
        id: 1,
        name: "Spaghetti Sunday",
        day: "Sunday"
        },
        {
          id: 2,
          name: 'Marinade Monday',
          day: "Monday"
        },
        {
        id: 3,
        name: "Taco Tuesday",
        day: "Tuesday"
        },
        {
        id: 4,
        name: "Wild-Wings Wednesday",
        day: "Wednesday"
        },
        {
        id: 5,
        name: "Three-Bean Thursday",
        day: "Thursday"
        },
        {
        id: 6,
        name: "Fajita Friday",
        day: "Friday"
        },
        {
        id: 7,
        name: "Sugary Saturday",
        day: "Saturday"
        },
    ],
      newMealPlan: {
        id: 0,
        name: "Test plan"
      }
    }
  }
}
  
</script>

<style scoped>
  
</style>
