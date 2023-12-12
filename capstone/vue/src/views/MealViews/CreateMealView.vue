<template>
  <h1>Create New Meal</h1>
  <router-link v-bind:to="{name: 'meals'}">Back to My Meals</router-link>
  <create-meal-form/>
</template>

<script>
import CreateMealForm from '../../components/Forms/CreateMealForm.vue'
export default {
  components: { CreateMealForm },

}
</script>

<style>

</style>