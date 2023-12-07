<template>
  <h1>My Meals</h1>
  <router-link v-bind:to="{name: 'create-meal'}"><p>Add Meal</p></router-link>
  <router-link v-bind:to="{name: 'meal-details', params: {id: 0}}">Meal Details [One of these for each meal!]</router-link>
</template>

<script>
export default {

}
</script>

<style>

</style>