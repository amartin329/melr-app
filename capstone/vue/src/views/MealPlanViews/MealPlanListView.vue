<template>
  <h1>My Meal Plans</h1>
    <meal-plan-list v-bind:plans="plans" />
</template>

<script>
import MealPlanList from '../../components/MealPlanList.vue'
import mealPlanService from '../../services/MealPlanService'
export default {
  components: { MealPlanList },
  data(){
    return {
        plans: [
        {
        id: 1,
        name: "Spaghetti Sunday",
        day: "Sunday",
        meals: [
                {
                name: 'Fettucini Alfredo and Eggs',
                id: 1,
                recipes: [
                    {
                        name: "Fettucini",
                        id: 1
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
            ]
        
        },
        {
          id: 2,
          name: 'Marinade Monday',
          day: "Monday",
          meals: [
                {
                name: 'Marinated Sausage',
                id: 3
                },
                {
                name: 'Marinated Beef',
                id: 4
                },
                {
                name: 'Marinated Chicken',
                id: 5
                }
            ]
        },
        {
        id: 3,
        name: "Taco Tuesday",
        day: "Tuesday"
        },
        {
        id: 4,
        name: "Wild-Wings Wednesday",
        day: "Wednesday"
        },
        {
        id: 5,
        name: "Three-Bean Thursday",
        day: "Thursday"
        },
        {
        id: 6,
        name: "Fajita Friday",
        day: "Friday"
        },
        {
        id: 7,
        name: "Sugary Saturday",
        day: "Saturday"
        },
    ],
        newMealPlan: {
            name: 'Check',
            id: 10
        }
    };
  },
  methods: {
    retrievePlans() {
        mealPlanService.getMealPlans().then(response => {
            this.plans = response.data;
        }).catch(error => {
            if(error.response){
                this.$store.commit('SET_NOTIFICATION',
                "Error retrieving meal plans. Response received: " + error.response.statusText);
            } else if(error.request){
                this.$store.commit('SET_NOTIFICATION', "Error retrieving meal plans. Server could not be reached.");
            }else{
                this.$store.commit('SET_NOTIFICATION', "Error retrieving meal plans. Request could not be created.");
            }
        });
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
    }
  },
//   created(){
//     this.retrievePlans();
//   }
    
}
</script>


<style>

</style>