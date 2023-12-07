<template>
   <router-link v-bind:to="{name: 'recipes'}">Back to My Recipes</router-link>
  <RecipeForm></RecipeForm>

</template>

<script>
import RecipeForm from '../../components/RecipeForm.vue';

export default {
    components: { 
      RecipeForm }
}
</script>

<style>

</style>