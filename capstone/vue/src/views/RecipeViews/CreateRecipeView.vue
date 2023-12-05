<template>
  <RecipeForm></RecipeForm>
  <IngredientForm></IngredientForm>
</template>

<script>
import RecipeForm from '../../components/RecipeForm.vue';
  import IngredientForm from '../../components/IngredientForm.vue';

export default {
    components: { 
      IngredientForm,
      RecipeForm }
}
</script>

<style>

</style>