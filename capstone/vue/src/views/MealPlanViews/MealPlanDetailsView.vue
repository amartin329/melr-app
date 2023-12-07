<template>
  <MealPlanDetails/>
  <router-link v-bind:to="{name: 'meals'}">Back to My Meal Plans</router-link>

</template>

<script>
import MealPlanDetails from '../../components/MealPlanDetails.vue';

export default {
    components: { MealPlanDetails }
}
</script>

<style>

</style>