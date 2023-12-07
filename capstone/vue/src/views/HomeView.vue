<template>
  <div class="home">
    <h1>This Week's Plan</h1>
    <h2>This will probably use our Schedule table!</h2>
    <h3>Our table should have 7 rows - 1 per day of the week.</h3>
    <h4>Each day of the week will be associated with a Meal Plan</h4>
    <schedule v-bind:plans="plans" />
  </div>
</template>

<script>
import Schedule from '../components/PlanSchedule.vue'
export default{
  components: { Schedule },
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
