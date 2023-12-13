<template>
  <div class="mealcard">
    <h2>{{ meal.mealName }}</h2>        
  </div>
</template>

<script>
export default {

  props: {
    meal: { type: Object, required: true }
  }

}
</script>

<style>

.mealcard {

  text-align: center;
  border: 2px solid black;
  background-color: orange;
  border-radius: 10px;
  margin: 20px;
  padding: 10px;
}


</style>