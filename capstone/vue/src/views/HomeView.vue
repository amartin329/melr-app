<template>
      <h1>Welcome, {{ this.$store.state.user.username }}!</h1>
      <h1>This week's plan:</h1>
  <div class="home">

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
