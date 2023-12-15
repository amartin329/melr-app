<template>
    <h1>{{ this.$store.state.user.username }}'s Recipes</h1>
    <div class="d-grid gap-1">
      <router-link class="btn btn-primary" v-bind:to="{name: 'create-recipe'}"><p>Create New Recipe</p></router-link>

    </div>

    <div class="recipe-list">
      <div id="recipeDetail" v-for="recipe in recipes" v-bind:key="recipe.id" class="recipe-container">
        <recipe-card v-bind:recipe="recipe" />
      </div>
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
.recipe-container{
  width: 48%;
  margin-bottom: 10px;
 /* margin-bottom: 10px; */
 /* display:flex;
 flex-direction: column; */
}

.recipe-list{
  display: flex;
  flex-wrap:wrap;
  justify-content: space-between;
 
}


</style>