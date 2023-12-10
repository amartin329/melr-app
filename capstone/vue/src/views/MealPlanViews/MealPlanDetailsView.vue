<template>
  <router-link v-bind:to="{name: 'plans'}">Back to My Meal Plans</router-link>
  <MealPlanDetails />

</template>

<script>
import MealPlanDetails from '../../components/MealPlanDetails.vue';

export default {
    components: { MealPlanDetails },

    
}
</script>

<style>

</style>