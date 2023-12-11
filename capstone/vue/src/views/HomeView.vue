<template>
  <div class="home">
    <h1>Welcome, {{ this.$store.state.user.username }}!</h1>
    <h1>This Week's Plan</h1>
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
