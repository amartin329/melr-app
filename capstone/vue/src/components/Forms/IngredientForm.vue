<!-- NOTE: THIS IS A MOCK-UP. 
    MUCH OF THIS WILL BE CHANGED WHEN DATABASE IS AVAILABLE. -->
<template>
    <div>
    <div class="ingredients">
        <h2>Recipe Ingredients</h2>
        <div class="ingredient" v-for="ingredient in recipeIngredients" v-bind:key="ingredient.id">
            <p>{{ ingredient.name }} | {{ ingredient.amount }}</p>
        </div>
    </div>
      <h3>Add ingredient:</h3>
      <div v-for="(result, name) in userResults" v-bind:key="name" class="pending-ingredients">
        <div class="pending-ingredient">
          <img v-bind:src="getImage(result.image)">
        {{ result.name }}
        <label :for="'amount-' + result.name">
            <input type="text" :id="'amount-' + result.name" placeholder="amount" v-model="this.result.amount">
        </label>
        <label for="unit">
            <select name="units" id="units" v-model="this.result.unit">
                <option>------</option>
                <option>ounces</option>
                <option>grams</option>
                <option>pounds</option>
                <option>pieces</option>
            </select>
        </label>
        <button v-on:click="getIngredientInformation(result)">Add Ingredient to Recipe</button>
        </div>
       
      </div>
      
      <input type="text" v-model="this.userSearch" id="userSearch"/>
      <button v-on:click="searchResults">Search for ingredient</button>
      
    </div>
  </template>
  
  <script>
import SpoonacularService from '../../services/SpoonacularService';
  export default {
    props: ['ingredients'],
    data(){
     return {
      userSearch: "",
      pendingIngredients: [],
      userResults: [],
      result: 
        {
          id: 0,
          image: "",
          name: "",
          amount: 0,
          unit: ""
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
      // addIngredient(ingredient){
      //   let newIngredient = this.getIngredientInformation(ingredient.id, ingredient.unit, ingredient.amount)
      //   this.recipeIngredients.push(newIngredient);
      // },

      getIngredientInformation(result){
        SpoonacularService.getIngredientInformation(result.id, result.unit, result.amount).then(response => {
          this.recipeIngredients.push(response);
        })
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
  
  <style scoped>
    .pending-ingredient{
      background-color: hotpink;
      width: 1000px;

    }
  </style>