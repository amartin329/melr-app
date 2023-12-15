<template>
    <router-link v-bind:key="mealplan.id" v-bind:to="{name: 'plan-details', params: {id: mealplan.mealplanId}}">
      <div class="card bm-3" style="max-width: 540px;">
    <div class="row g-0">
      <div class="col-md-4">
        <img src="../assets/melr.png" class="img-fluid rounded-start">
      </div>
      <div class="col-md-8">
        <div class="card-body">
        <!-- <h5 class="card-title ">{{ recipe.recipeName }}</h5> -->
        <h5 class="card-title">{{ mealplan.mealplanName }}</h5>

        
        

        </div>
      </div>
    </div>
   
    
   
    
  </div>
    </router-link>
<router-link v-bind:key="mealplan.id" v-bind:to="{name: 'plan-details', params: {id: mealplan.mealplanId}}">

</router-link>
</template>

<script>
export default {

    props: {
        mealplan: { type: Object, required: true }
    }
}
</script>

<style>
.gregMealplancard {
    text-align: center;
    border: 2px solid black;
    background-color:blueviolet;
    border-radius: 10px;
    margin: 20px;
    padding: 10px;
}

.card{
    background-color: #95e0dd;
}
</style>