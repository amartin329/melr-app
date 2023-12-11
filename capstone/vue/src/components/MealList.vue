<template>
    <h1>{{ this.$store.state.user.username }}'s Meals</h1>
    <router-link v-bind:to="{name: 'create-meal'}"><p>Create New Meal</p></router-link>
    <div class="meals">
        <div class="meal" v-for="meal in mealStoreList" v-bind:key="meal">
            <router-link v-bind:key="meal.mealId"
      v-bind:to="{name: 'meal-details', params: {id: meal.mealId}}">
          <div class="meal">{{ meal.name }}</div>
          <p v-on:click="currentMeal">{{ meal.mealName }}</p>
      </router-link>
        </div>

    </div> 
      
  </template>
  
  <script>
  export default {
    //   props: ['meals']
    methods:{
        getMeals(){
            this.$store.dispatch('getMeals');
        },
        currentMealPlan(id){
            this.$store.dispatch('getMealById', id)
        }
    },
    created(){
        this.getMeals();
        console.log(this.$store.state.meals)
    },
    computed: {
        mealStoreList(){
            return this.$store.state.meals;
        }
    }
  }
  </script>
  
  <style>
  
  </style>