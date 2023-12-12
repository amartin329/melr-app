<template>
  <div class="meal-plans">
      <form v-for="day in storeSchedule" @submit.prevent="addPlanToSchedule(day)" 
        v-bind:key="day.day">
        <div class="day">
            <h4>{{ day.day }}</h4>  
            <router-link v-if="day.mealplanName!=''" v-bind:to="{name: 'plan-details', params: {id: day.id}}">
                <p>{{ day.mealplanName }}</p>
            </router-link>
            <div v-else>
                
            <select id="options" v-model="chosenPlan">    
                    <option value="-">Pick A Meal Plan</option>
                    <option v-for="storePlan in storePlans" v-bind:key="storePlan.mealPlanId" value="storePlan.mealplanId">{{ storePlan.mealplanName }}</option>
            </select>
            <button>Add Plan to Schedule</button>


            </div>
           <!-- <add-meal-to-schedule class="add-plan" v-if="this.$store.state.formIsVisible"></add-meal-to-schedule> -->
            
        </div>
          
    </form>
        
    </div>
</template>

<script>
export default {
    data(){
    return{
        mealPlans:[],
        chosenPlan: {},
        pendingSchedule: 
        {
        sunday:{
          day: "Sunday:",
          mealplanName: "",
          id: 1,

        },
        monday:{
          day: "Monday",
          mealplanName: "",
          id: 2
        },
        tuesday:{
          day: "Tuesday",
          mealplanName: "",
          id: 3
        },
        wednesday: {
          day: "Wednesday",
          mealplanName: "",
          id: 4
        },
        thursday: {
          day: "Thursday",
          mealplanName: "",
          id: 5
        },
        friday: {
          day: "Friday",
          mealplanName: "",
          id: 6
        },
        saturday: {
          day: "Saturday",
          mealplanName: "",
          id: 7
        }
      },
    //   schedule: [
    //     {
    //     id: 1,
    //     name: "Spaghetti Sunday",
    //     day: "Sunday"
    //     },
    //     {
    //       id: 2,
    //       name: 'Marinade Monday',
    //       day: "Monday"
    //     },
    //     {
    //     id: 3,
    //     name: "Taco Tuesday",
    //     day: "Tuesday"
    //     },
    //     {
    //     id: 4,
    //     name: "Wild-Wings Wednesday",
    //     day: "Wednesday"
    //     },
    //     {
    //     id: 5,
    //     name: "Three-Bean Thursday",
    //     day: "Thursday"
    //     },
    //     {
    //     id: 6,
    //     name: "Fajita Friday",
    //     day: "Friday"
    //     },
    //     {
    //     id: 7,
    //     name: "Sugary Saturday",
    //     day: "Saturday"
    //     },
    // ],
    }

    },
    methods:{
        getPlans(){
            this.$store.dispatch('getMealPlans');
      
        },
        addPlanToSchedule(plan){
            this.$store.commit('updateSchedule', {day: plan.day, mealplanId: chosenPlanId})
        }
    },
    computed: {
        storeSchedule(){
            return this.$store.state.schedule
        },
        storePlans(){
            return this.$store.state.mealPlans;
        }
  },
  created(){
    this.getPlans();
         console.log(this.storePlans)
  }
  
    }


</script>

<style>
    .meal-plans{
        /* display:flex;
        flex-direction: row;
        justify-content: space-between; */
       
        display: grid;
        grid-template-areas:
        "meal-plan meal-plan meal-plan meal-plan"
        "space meal-plan meal-plan meal-plan";
        grid-template-rows: 1fr 1fr;
        grid-template-columns: 1fr 1fr 1fr 1fr;
        height: 40vh;
      
    }
    .day{
        background-color: lightcoral;
        border: 3px black solid;
        height:100%;
        grid-area: meal-plan;
        width: 99%;
      
    }

</style>