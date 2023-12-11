<template>

  <router-link v-bind:to="{name: 'meals'}">Back to My Meals</router-link>
  <mealDetails />
</template>

<script>
import mealDetails from '../../components/MealDetails.vue';
export default {
components: {
mealDetails
},

}
</script>

<style>

</style>