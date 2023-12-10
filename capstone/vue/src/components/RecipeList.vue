<template>
    <h1>Recipes</h1>
    <router-link v-bind:to="{name: 'create-recipe'}"><p>Create New Recipe</p></router-link>
    <div class="recipes">
      <router-link v-for="recipe in recipes" v-bind:key="recipe.recipeId"
      v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}">
          <div class="recipe">
            <p>RECIPE NAME: {{ recipe.recipeId }}</p>
            
        </div>
      </router-link>
    </div>
  </template>
  
  <script>
  export default {
      props: ['recipes']
  }
  </script>
  
  <style>
  
  </style>