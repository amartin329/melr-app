<template>
  <div v-if="this.ingredientsList.length == 0">
    <h1>Your grocery list is looking mighty lonesome...</h1>
    <h2>Add some meal plans to your schedule, and everything you need for them will auto-magically go here!</h2>
  </div>
  <div v-else class="ingredient" v-for="ingredient of this.ingredientsList" v-bind:key="ingredient.ingId">
    <p>{{ingredient.ingName}}</p>
    <p>{{ ingredient.quantity }}</p>
  </div>
</template>

<script>
export default {
  data(){
    return{
      ingredientsList: []
    }
  },
methods: {
  getGroceries(){
    if (this.storeSchedule.sunday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.sunday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}

    if (this.storeSchedule.monday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.monday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}
if (this.storeSchedule.tuesday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.monday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}

if (this.storeSchedule.wednesday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.wednesday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}

if (this.storeSchedule.thursday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.thursday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}

if (this.storeSchedule.friday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.friday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}

if (this.storeSchedule.saturday?.mealList?.[0]?.recipeList?.[0]?.ingredientList) {
  for (let ingredient of this.storeSchedule.saturday.mealList[0].recipeList[0].ingredientList) {
    if (ingredient) {
      this.ingredientsList.push(ingredient);
    }
  }
}






     
    
     
  }
},
computed:{
  storeSchedule(){
            return this.$store.state.schedule
        },
  // groceryList(){
  //   return this.getGroceries
  // }
},

created(){
  this.getGroceries()
}

}
</script>

<style>

</style>