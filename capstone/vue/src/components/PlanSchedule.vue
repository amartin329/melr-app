<template>
  <div class="meal-plans">
      <router-link 
        v-for="plan in plans" 
        v-bind:key="plan.id"
        v-bind:to="{name: 'plan-details', params: {id: plan.id}}">
        <div class="meal-plan">
            <h4>{{ plan.day }}</h4>  
            <p>{{ plan.name }}</p>
        </div>
          
      </router-link>
        
    </div>
</template>

<script>
export default {
    props:['plans']
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
    .meal-plan{
        background-color: lightcoral;
        border: 3px black solid;
        height:100%;
        grid-area: meal-plan;
        width: 99%;
      
    }

</style>