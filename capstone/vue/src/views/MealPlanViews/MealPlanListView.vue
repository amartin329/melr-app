<template>
  <h1>My Meal Plans</h1>
  <router-link v-bind:to="{name: 'create-plan'}"><p>Create New Meal Plan</p></router-link>
  <router-link v-bind:to="{name: 'plan-details', params: {id: 0}}">Meal Plan Details [One of these for each meal plan!]</router-link>
</template>

<script>
export default {

}
</script>

<style>

</style>