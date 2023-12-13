<template>
  <div id="mealDetail" v-for="meal in meals" v-bind:key="meal.id">
    <router-link v-bind:key="meal.id" v-bind:to="{name: 'meal-details', params: {id: meal.mealId}}">
      <meal-card v-bind:meal="meal" />
    </router-link>
  </div>


         <!--meal-list went here before I deleted it - Greg-->
</template>

<script>
import MealList from '../../components/MealList.vue'
import MealService from '../../services/MealService'
import MealCard from '../../components/MealCard.vue'

export default {
    components:{
        MealCard
    },

    data() {
      return {
        meals:[]
      }
    },

    created() {
      MealService.getMeals().then( response => {
        this.meals = response.data;
      })
    },

}
</script>

<style>

</style>