<template>
  <h1>My Recipes</h1>
  <router-link v-bind:to="{name: 'create-recipe'}"><p>Add Recipe</p></router-link>
  <router-link v-bind:to="{name: 'recipe-details', params: {id: 0}}">Recipe Details [One of these for each recipe!]</router-link>
</template>

<script>
export default {

}
</script>

<style>

</style>