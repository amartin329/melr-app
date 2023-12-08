<template>
    <h1>My Meal Plans</h1>
    <router-link v-bind:to="{name: 'create-plan'}"><p>Create New Meal Plan</p></router-link>
    <div class="plans">
      <router-link v-for="plan in plans" v-bind:key="plan.id"
      v-bind:to="{name: 'plan-details', params: {id: plan.id}}">
          <div class="plan">{{ plan.name }}</div>
      </router-link>
    </div>
  </template>
  
  <script>
  export default {
      props: ['plans']
  }
  </script>
  
  <style>
  
  </style>