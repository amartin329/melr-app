<template>
  <h1>Create New Meal Plan</h1>
  <router-link v-bind:to="{name: 'plans'}">Back to My Meal Plans</router-link>
    <create-meal-plan-form />

</template>

<script>
import CreateMealPlanForm from "../../components/Forms/CreateMealPlanForm.vue"

export default {
  components: { CreateMealPlanForm }
}
</script>

<style>

</style>