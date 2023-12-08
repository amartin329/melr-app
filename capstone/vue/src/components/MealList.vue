<template>
    <h1>My Meals</h1>
    <router-link v-bind:to="{name: 'create-meal'}"><p>Create New Meal</p></router-link>
    <div class="meals">
      <router-link v-for="meal in meals" v-bind:key="meal.id"
      v-bind:to="{name: 'meal-details', params: {id: meal.id}}">
          <div class="meal">{{ meal.name }}</div>
      </router-link>
    </div>
  </template>
  
  <script>
  export default {
      props: ['meals']
  }
  </script>
  
  <style>
  
  </style>