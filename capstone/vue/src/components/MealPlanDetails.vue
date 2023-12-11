<template>
  <h1>{{ this.$store.state.currentMealPlan.mealplanName}}</h1>
  <div class="meal" v-for="meal in this.$store.state.currentMealPlan.mealList" v-bind:key="meal.mealId">
    <router-link v-bind:key="meal.mealId"
    v-bind:to="{name: 'meal-details', params: {id: meal.mealId}}">
        <p>{{ meal.mealName }}</p>
    </router-link>
  </div>
  <addMealToPlanForm v-if="addMealIsVisible" class="popup"></addMealToPlanForm>
  <button v-if="!addMealIsVisible" v-on:click.prevent="toggleAddMeal">Add Meal to Meal Plan</button>
  <button v-if="addMealIsVisible" v-on:click.prevent="toggleAddMeal">Exit without saving</button>

</template>

<script>
import addMealToPlanForm from './Forms/AddMealToPlanForm.vue'

export default {
    data() {
        return {
            addMealIsVisible: false,
            planId: this.$route.params.id
        };
    },
    methods: {
        getCurrentMealPlan(mealPlanId) {
            this.$store.dispatch('getMealPlanById', mealPlanId);
        },
        toggleAddMeal() {
            this.addMealIsVisible = !this.addMealIsVisible
        },

    
    },
    created() {
        console.log(this.planId);
        this.getCurrentMealPlan(this.planId);
    }
    // props: ['mealPlan']
    ,
    components: { addMealToPlanForm }
}
</script>

<style>
.popup{
    width:500px;
 height:500px;
 margin:0 auto;
 background:#f7f7f7;
 position:absolute;
 left:50%;
 top:50%;
 margin-left:-250px;
 margin-top:-250px;
 border: 5px solid black;
 border-radius: 5px;
}
</style>