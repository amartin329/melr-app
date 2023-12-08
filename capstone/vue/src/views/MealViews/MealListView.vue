<template>
  <h1>My Meals</h1>
  <router-link v-bind:to="{name: 'create-meal'}"><p>Add Meal</p></router-link>
  <meal-list v-bind:meals="meals" />
</template>

<script>
import MealList from '../../components/MealList.vue'
import MealService from '../../services/MealService'
export default {
    components:{
        MealList
    },
    data(){
        return{
            meals: [
                {
                name: 'Bacon and Eggs',
                id: 1
                },
                {
                name: 'Roast Beef and French Onion Soup',
                id: 2
                },
                {
                name: 'Sushi Delight',
                id: 2
                }
            ],
        }
    }
}
</script>

<style>

</style>