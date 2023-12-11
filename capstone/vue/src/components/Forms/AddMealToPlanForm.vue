<template>
  <h2>Add Meal to {{ this.$store.state.currentMealPlan.mealplanName }}</h2>
  <!-- Populate list of user's meal plans -->
  <!-- For each, have checkbox that allows a user to add it to plan -->
  <div v-for="meal in this.$store.state.meals" v-bind:key="meal.mealId">
    <h3>{{meal.mealName}}</h3>
    <button v-on:click.prevent="addMealToPlan(this.planId, 5)">Add Meal</button>
</div>
  
</template>

<script>
export default {
    data(){
        return{
            formIsVisible: true,
            planId: this.$route.params.id
        }
    },


    methods:{
        getMeals(){
            return this.$store.dispatch('getMeals')
        },

        addMealToPlan(planId, mealId){
            this.$store.dispatch('addMealToPlan', planId, mealId)
        }
    },
    created(){
        this.getMeals();
    }
}
</script>

<style>
.form {
  padding: 10px;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}

label {
  display: inline-block;
  margin-bottom: 0.5rem;
}

.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}

textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}

select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}

</style>