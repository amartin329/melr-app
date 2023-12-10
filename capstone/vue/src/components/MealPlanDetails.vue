<template>
  <h1>{{ this.$store.state.currentMealPlan.mealplanName}}</h1>
  <div class="meal" v-for="meal in this.$store.state.currentMealPlan.mealList" v-bind:key="meal.id">
    <h3>AOAOIH{{meal.mealName}}</h3>
  </div>

  <!--  -->
</template>

<script>
export default {
data(){
    return{
        mealPlanId: this.$route.params.id
    }
},

    methods: {
         getCurrentMealPlan(mealPlanId){
            this.$store.dispatch('getMealPlanById', mealPlanId);
        },
        getId(){}
    },
    created(){
        console.log(this.$route.params.id)
        this.$store.dispatch('getMealPlanById', this.$route.params.id);
    }
    // props: ['mealPlan']
}
</script>

<style>

</style>