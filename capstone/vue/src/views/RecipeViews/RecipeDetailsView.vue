<template>
  <router-link v-bind:to="{name: 'recipes'}">Back to My Recipes</router-link>
  <recipe-details/>
</template>

<script>
import RecipeDetails from '../../components/RecipeDetails.vue'

export default {
  components: { RecipeDetails },

}
</script>

<style>

</style>