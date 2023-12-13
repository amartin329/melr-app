<template>
  <div class="card">
    <h2>{{ recipe.recipeName }}</h2>
    <img v-bind:src="recipe.picturePath" />
  </div>
</template>

<script>
export default {

  props: {
    recipe: { type: Object, required: true }
  }

}
</script>

<style>

.card {  
  text-align: center;
  border: 2px solid black;
  background-color:rgb(200,250, 400);
  border-radius: 10px;
  margin: 20px;
  padding: 10px;
}


</style>