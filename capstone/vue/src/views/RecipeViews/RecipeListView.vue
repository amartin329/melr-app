<template>
    <div id="recipeDetail" v-for="recipe in recipes" v-bind:key="recipe.id">
    <router-link v-bind:key="recipe.id" v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}">
      <recipe-card v-bind:recipe="recipe" />
    </router-link>
  </div>
  <!--recipeList went here before I deleted it - Greg-->
</template>

<script>
import recipeList from '../../components/RecipeList.vue'
import recipeService from '../../services/RecipeService'
import recipeCard from '../../components/RecipeCard.vue'

export default {
components: {recipeCard},

  data() {
    return {
      recipes:[]
    }
  },

  created() {
    recipeService.getRecipes().then( response => {
      this.recipes = response.data;
    })
  },

}
</script>

<style>

</style>