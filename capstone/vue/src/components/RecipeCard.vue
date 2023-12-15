<template>
    <router-link v-bind:key="recipe.id" v-bind:to="{name: 'recipe-details', params: {id: recipe.recipeId}}">
      <div class="card bm-3" style="max-width: 540px;">
    <div class="row g-0">
      <div class="col-md-4">
        <img src="../assets/melr.png" class="img-fluid rounded-start">
      </div>
      <div class="col-md-8">
        <div class="card-body">
        <!-- <h5 class="card-title ">{{ recipe.recipeName }}</h5> -->
        <h5 class="card-title">{{ recipe.recipeName }}</h5>
        <p class="card-text" v-if="recipe.ingredientList[0]">
          <small class="text-body-secondary">
          {{ recipe.ingredientList[0].ingName }}, 
        </small>
        <br>
        <small class="text-body-secondary" v-if="recipe.ingredientList[1]">
          {{ recipe.ingredientList[1].ingName }}...
        </small>
      
      </p>
        

        </div>
      </div>
    </div>
   
    
   
    
  </div>
    </router-link>
 
</template>

<script>
export default {

  props: {
    recipe: { type: Object, required: true }
  }

}
</script>

<style>

.gregCard {  
  text-align: center;
  border: 2px solid black;
  background-color:rgb(200,250, 400);
  border-radius: 10px;
  margin: 20px;
  padding: 10px;
}

h2{
  text-decoration: none;
}
/* 
.card{
  background-color:rgb(200,250, 400);
  width: 18rem;
  max-height: 22rem;
} */

.text-body-secondary{
  font-size: .9rem;
}

.card-title{
  text-wrap: pretty;
}

</style>