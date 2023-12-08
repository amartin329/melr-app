<template>
  <router-link v-bind:to="{name: 'plans'}">Back to My Meal Plans</router-link>
  <MealPlanDetails v-bind:meals="planMeals"/>

</template>

<script>
import MealPlanDetails from '../../components/MealPlanDetails.vue';

export default {
    components: { MealPlanDetails },
    data(){
      return{
        mealPlan: {name: 'All-Day Pasta Plan',
         meals: [
                {
                name: 'Fettucini Alfredo and Eggs',
                id: 1,
                recipes: [
                    {
                        name: "Fettucini",
                        id: 1,
                        ingredients: [
                          {
                            name: "Noodles",
                          },
                          {
                            name: "Eggs"
                          }
                        ]
                    },
                    {
                        name: "Eggs",
                        id: 2
                    }
                ]
                },
                {
                name: 'Ramen and Toast',
                id: 2
                },
                {
                name: 'Spaghetti and Meatballs w/ Garlic Bread',
                id: 2
                }
            ]}
      }
    },
    computed: {
      planMeals(){
        return this.mealPlan.meals;
      },
    }
    
}
</script>

<style>

</style>