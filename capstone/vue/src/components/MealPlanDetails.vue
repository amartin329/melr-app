<template>
  <h1>{{ this.$store.state.currentMealPlan.mealplanName}}</h1>

    <div class="meal" v-for="meal in this.$store.state.currentMealPlan.mealList" v-bind:key="meal.mealId">
    <router-link v-if="!addMealIsVisible" v-bind:key="meal.mealId"
    v-bind:to="{name: 'meal-details', params: {id: meal.mealId}}">
        <p>{{ meal.mealName }}</p>
    </router-link>
    <div class="meal-delete" v-else>
        <p>{{ meal.mealName }}</p>
        <button v-on:click.prevent="removeMealFromPlan(this.planId, meal.mealId)">Remove Meal</button>
    </div>
  </div>
  
  <addMealToPlanForm v-if="addMealIsVisible" class="popup"></addMealToPlanForm>
  <button v-if="!addMealIsVisible" v-on:click.prevent="toggleEdit">Edit Meal Plan</button>
  <button v-if="addMealIsVisible" v-on:click.prevent="toggleEdit">Cancel</button>

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
        toggleEdit() {
            this.addMealIsVisible = !this.addMealIsVisible
        },

        removeMealFromPlan(planId, mealId){
            this.$store.dispatch('removeMealFromPlan', {mealplanId: planId, mealId: mealId})
            this.toggleEdit();
        }

    
    },
    created() {
        console.log(this.planId);
        this.getCurrentMealPlan(this.planId);
        this.addMealIsVisible = false;
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