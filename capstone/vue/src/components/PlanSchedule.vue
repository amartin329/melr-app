<template>
  <div class="schedule-container">
      <form>
        <div class="meal-plans">
       <div v-for="day in storeSchedule"
        v-bind:key="day.day" class="card schedule" id="schedule">
        <div class="card-body">
            <h4>{{ day.day }}</h4>  
            <router-link v-if="!isEditable" v-bind:to="{name: 'plan-details', params: {id: day.mealplanId}}">
                <h5 class="card-title">{{ day.mealplanName }}</h5>
            </router-link>
            <div v-else>
            <select id="options" v-model="this.selectedPlan[day.day]"
            @change="setMealPlan(day.day, selectedPlan)">    
                    <option value="-" disabled>Pick A Meal Plan</option>
                    <option v-for="storePlan in storePlans" v-bind:key="storePlan.mealPlanId" :value="storePlan">{{ storePlan.mealplanName }}</option>
            </select>
            </div>
           <!-- <add-meal-to-schedule class="add-plan" v-if="this.$store.state.formIsVisible"></add-meal-to-schedule> -->
        </div>
    </div>
    <div class="update-schedule-confirm card" id="schedule" v-if="isEditable">
            <button v-on:click.prevent="addPlanToSchedule(this.pendingSchedule)">Confirm Changes</button>
            <button v-on:click.prevent="flagEditable">Cancel</button>
        </div>
        <div class="update-schedule card" id="schedule" v-if="!isEditable">
            <button v-on:click.prevent="flagEditable">Edit Schedule</button>
        </div>
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
        isEditable: false,
        pendingSchedule: 
        {
        sunday:{
          day: "sunday",
          mealplanName: "",
          mealplanId: 1,

        },
        monday:{
          day: "monday",
          mealplanName: "",
          mealplanId: 2
        },
        tuesday:{
          day: "tuesday",
          mealplanName: "",
          mealplanId: 3
        },
        wednesday: {
          day: "wednesday",
          mealplanName: "",
          mealplanId: 4
        },
        thursday: {
          day: "thursday",
          mealplanName: "",
          mealplanId: 5
        },
        friday: {
          day: "friday",
          mealplanName: "",
          mealplanId: 6
        },
        saturday: {
          day: "saturday",
          mealplanName: "",
          mealplanId: 7
        }
      },

      selectedPlan: {
        sunday:{
        
        },
        monday:{
      
        },
        tuesday:{

        },
        wednesday:{

        },
        thursday:{

        },
        friday:{

        },
        saturday:{

        }

      }
    }

    },
    methods:{
        flagEditable(){
            this.isEditable = !this.isEditable;
        },
        getPlans(){
            this.$store.dispatch('getMealPlans');
      
        },
        addPlanToSchedule(schedule){
            console.log("PENDING SCHEDULE: " + this.pendingSchedule)
            console.log(schedule.monday);
          
            console.log(this.pendingSchedule);
            this.$store.dispatch('updateSchedule', schedule);
            this.isEditable = !this.isEditable;
            console.warn(this.$store.state.schedule);

      
        },
        setMealPlan(day, plan){
            console.warn(plan)
            console.log(this.pendingSchedule[day])
            this.pendingSchedule[day].mealplanName = plan[day].mealplanName;
            this.pendingSchedule[day].mealplanId = plan[day].mealplanId;
            this.pendingSchedule[day].mealList = plan[day].mealList;
            console.log(this.pendingSchedule[day])
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
        display:flex;
        /* flex-direction: column; */
        justify-content: space-between;
        flex-wrap: wrap;
        width: 100%;
       
        /* display: grid;
        grid-template-areas:
        "meal-plan meal-plan meal-plan meal-plan"
        "space meal-plan meal-plan meal-plan"; */
        /* grid-template-rows: 1fr 1fr;
        grid-template-columns: 1fr 1fr 1fr 1fr; */
   
      
    }
    /* .day{
        background-color: lightcoral;
        border: 3px black solid;
        height:5%;
        grid-area: meal-plan;
        width: 99%;
      
    } */

    .schedule-container{
      width: 100%;

    }

    #schedule{
      width: 48%;
      margin-bottom: 10px;

      height: 115px;
    }
    form{
      /* width: 100%; */
    }

</style>