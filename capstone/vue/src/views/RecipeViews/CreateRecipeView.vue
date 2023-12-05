<template>
    <div>
      <h1>Search for an ingredient!</h1>
      <div v-for="(result, name) in userResults" v-bind:key="name">
        {{ result.name }}
        <img v-bind:src="getImage(result.image)">
      </div>
      
      <input type="text" v-model="this.userSearch" id="userSearch"/>
      <button v-on:click="searchResults">SEARCH FOR {{ this.userSearch }}!</button>
      
    </div>
  </template>
  
  <script>
  import SpoonacularService from '../../services/SpoonacularService';
  export default {
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
      foundIngredients: [],
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

     // getIngredientInformation(this.result.id, this.resul){
        
      //}
      
    },
    created(){
      this.getResults();
    }
  };
  </script>
  