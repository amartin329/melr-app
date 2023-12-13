<template>
    <h1>{{ this.$store.state.user.username }}'s Meal Plans</h1>
    <router-link v-bind:to="{name: 'create-plan'}"><p>Create New Meal Plan</p></router-link>
    <div class="plans">
        <div class="plan" v-for="mealPlan in mealPlanStoreList" v-bind:key="mealPlan">
            <router-link v-bind:key="mealPlan.mealplanId"
            v-bind:to="{name: 'plan-details', params: {id: mealPlan.mealplanId}}"
            >
            <p v-on:click="currentMealPlan">{{ mealPlan.mealplanName }}</p>
        </router-link>
        
    </div>
    </div>
    
    <!-- <div class="plans">
      <router-link v-for="plan in plans" v-bind:key="plan.mealplanId"
      v-bind:to="{name: 'plan-details', params: {id: plan.mealplanId}}">
          <div class="plan">
            <p>PLAN NAME: {{ plan.mealplanName }}</p>
            
        </div>
      </router-link>
    </div> -->
  </template>
  
  <script>
  import mealPlanService from '../services/MealPlanService';
  export default {
    methods: {
    retrievePlans() {
        this.$store.dispatch('getMealPlans');
        // mealPlanService.getMealPlans().then(response => {
        //     this.plans = response.data;
        // }).catch(error => {
        //     if(error.response){
        //         this.$store.commit('SET_NOTIFICATION',
        //         "Error retrieving meal plans. Response received: " + error.response.statusText);
        //     } else if(error.request){
        //         this.$store.commit('SET_NOTIFICATION', "Error retrieving meal plans. Server could not be reached.");
        //     }else{
        //         this.$store.commit('SET_NOTIFICATION', "Error retrieving meal plans. Request could not be created.");
        //     }
        // });
    },
    validateAddPlan(){
        //ENSURE THAT A MEAL PLAN HAS ALL REQUIRED ATTRIBUTES.
        //REQUIRED ATTRIBUTES ARE:
    },

    saveNewPlan(){
        if(!this.validateAddPlan()){
            return;
        }
        mealPlanService.addMealPlan(this.newMealPlan).then(response =>{
            if(response.status === 201){
                let newMealPlan = response.data;
                this.$store.commit('SET_NOTIFICATION',
                {
                    message: `Successfully added "${newMealPlan.name}" to your meal plans.`,
                    type: 'success'
                });
                this.retrievePlans();
            }
        })
    },
    currentMealPlan(id){
        this.$store.dispatch('getMealPlanById', id)
    }
  },
  created(){
    // alert("Retrieving plans from Created");
    this.retrievePlans();
  },
    //   props: ['plans'],
    computed: {
        mealPlanStoreList(){
        return this.$store.state.mealPlans;
        }
      }
  }
  </script>
  
  <style>
  
  </style>