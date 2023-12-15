<template>
      <h1>{{ this.$store.state.user.username }}'s Meals</h1>
      <div class="d-grid gap-2">
      <router-link class= "btn btn-primary" v-bind:to="{name: 'create-meal'}"><p>Create New Meal</p></router-link>
    </div>
<div class="meal-list">
  <div id="mealDetail" v-for="meal in meals" v-bind:key="meal.id" class="meal-container">
   <meal-card v-bind:meal="meal" />
</div>

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
.meal-container{
  width: 48%;
  margin-bottom: 10px;
 /* margin-bottom: 10px; */
 /* display:flex;
 flex-direction: column; */
}

.meal-list{
  display: flex;
  flex-wrap:wrap;
  justify-content: space-between;
 
}
</style>