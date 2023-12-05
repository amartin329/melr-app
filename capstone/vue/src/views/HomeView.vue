<template>
  <div class="home">
    <h1>Home</h1>
    <h1>Here is a test view for our server-side endpoints! Wow!</h1>
    <h2>Here are some potatoes....check it out!</h2>
    <p v-for="(result, name) in results" v-bind:key="name">{{ result.name }}</p>

    <h1>But what if I want to search for something else? Type 'er in and click the button!</h1>
    <div v-for="(result, name) in userResults" v-bind:key="name">
      {{ result.name }}
      <img v-bind:src="getImage(result.image)">
    </div>
    
    <input type="text" v-model="this.userSearch" id="userSearch"/>
    <button v-on:click="searchResults">SEARCH FOR {{ this.userSearch }}!</button>
    
  </div>
</template>

<script>
import SpoonacularService from '../services/SpoonacularService';
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
    }
    
  },
  created(){
    this.getResults();
  }
};
</script>
