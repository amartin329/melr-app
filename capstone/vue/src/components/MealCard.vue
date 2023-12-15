<template>
<router-link v-bind:key="meal.id" v-bind:to="{name: 'meal-details', params: {id: meal.mealId}}">
  <div class="card bm-3" style="max-width: 540px;">
    <div class="row g-0">
      <div class="col-md-4">
        <img src="../assets/melr.png" class="img-fluid rounded-start">
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h5 class="card-title">{{ meal.mealName }}</h5>
          <p class="card-text" v-if="meal.recipeList[0]">
            <small class="text-body-secondary">
              {{ meal.recipeList[0].recipeName }},
            </small>
            <br>
            <small class="text-body-secondary" v-if="meal.recipeList[1]">
              {{ meal.recipeList[1].recipeName }}...
            </small>
          </p>
          <p class="card-text" v-else>
            <small class="text-body-secondary">Add some recipes!</small></p>
        </div>
      </div>
    </div>
  </div>
</router-link>
</template>

<script>
export default {

  props: {
    meal: { type: Object, required: true }
  }

}
</script>

<style>

.gregMealcard {

  text-align: center;
  border: 2px solid black;
  background-color: orange;
  border-radius: 10px;
  margin: 20px;
  padding: 10px;
}

.text-body-secondary{
  font-size: .9rem;
}


</style>