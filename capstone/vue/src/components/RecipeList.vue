<template>
    <h1>{{ this.$store.state.user.username }}'s Recipes</h1>
    <router-link v-bind:to="{name: 'create-recipe'}"><p>Create New Recipe</p></router-link>
    <div class="recipes">
        <div class="recipe" v-for="recipe in recipeStoreList" v-bind:key="recipe">
            <router-link v-bind:key="recipe.recipeId"
            v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}">
            <p>{{ recipe.recipeName }}</p>
      </router-link>
        </div>
     
    </div>
  </template>
  
  <script>
  export default {
    //   props: ['recipes']
    methods:{
        getRecipes(){
        this.$store.dispatch('getRecipes');
        },
    },
    created(){
        this.getRecipes();
    },
    computed:{
        recipeStoreList(){
            return this.$store.state.recipes;
        }
    }

  }
  </script>
  
  <style>
  
  </style>