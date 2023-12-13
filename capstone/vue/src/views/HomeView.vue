<template>
  <div class="home">
    <h1>Welcome, {{ this.$store.state.user.username }}!</h1>
    <h1>This Week's Plan</h1>
    <schedule></schedule>
  </div>
</template>

<script>
import Schedule from '../components/PlanSchedule.vue'
export default{
  components: { Schedule },

  }

</script>

<style scoped>
  
</style>
