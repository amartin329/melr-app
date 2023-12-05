<template>
    <div>
    <div class="ingredients">
        <h2>Recipe Ingredients</h2>
        <div class="ingredient" v-for="ingredient in recipeIngredients" v-bind:key="ingredient.id">
            <h3>{{ ingredient.name }}</h3>
        </div>
    </div>
      <h1>NEW INGREDIENT!</h1>
      <div v-for="(result, name) in userResults" v-bind:key="name">
        {{ result.name }}
        <img v-bind:src="getImage(result.image)">
        <button v-on:click="addIngredient(result)">Add Ingredient to Recipe</button>
      </div>
      
      <input type="text" v-model="this.userSearch" id="userSearch"/>
      <button v-on:click="searchResults">SEARCH FOR {{ this.userSearch }}!</button>
      
    </div>
  </template>
  
  <script>
import SpoonacularService from '../services/SpoonacularService';
  export default {
    props: ['ingredients'],
    data(){
     return {
      userSearch: "",
      results: [],
      userResults: [],
      result: 
        {
          id: 0,
          image: "",
          name: ""
        },
      recipeIngredients: [],
      ingredient: 
      {
        amount: 0,
        id: 0,
        image: "",
        name: "",
        nutritionDTO: 
          {
            nutritionId: 0,
            calories: 0,
            protein: 0,
            fat: 0
          },
        possibleUnits:[],
        unit: "",
        unitLong: "",
        unitShort: ""
      }
     } 
    },
    methods: {
      getResults(){
        SpoonacularService.getResults().then(response => {
        this.results = response.data;
      });
      },
      searchResults(){
        SpoonacularService.searchResults(this.userSearch).then(response => {
          this.userResults = response.data;
        });
      },
      getImage(image){
        return 'https://spoonacular.com/cdn/ingredients_100x100/' + image;
      },
      addIngredient(ingredient){
        this.recipeIngredients.push(ingredient);
      }

     // getIngredientInformation(this.result.id, this.resul){
        
      //}
      
    },
    computed: {
        // filteredIngredients(){
        //     let filteredIngredients = this.results;
        //     if(this.userSearch != ""){
        //         filteredIngredients =
        //     }
        // }
    },
    created(){
      this.getResults();
    }
  };
  </script>
  