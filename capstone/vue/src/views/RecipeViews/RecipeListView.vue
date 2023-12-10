<template>
  <h1>My Recipes</h1>
  <recipeList v-bind:recipes="recipes" />
</template>

<script>
import recipeList from '../../components/RecipeList.vue'
import recipeService from '../../services/RecipeService'

export default {
components: {recipeList},
data(){
    return {
        recipe:
        {
            recipeId: "",
            recipeName: "",
        },
        recipes: [],
    };
    },
    methods: {
        retrieveRecipes(){
            recipeService.getRecipes().then(response => {
                this.recipes = response.data;
            }).catch(error => {
                if(error.response){
                    this.$store.commit('SET_NOTIFICATION',
                "Error retrieving recipes. Response received: " + error.response.statusText);
            } else if(error.request){
                this.$store.commit('SET_NOTIFICATION', "Error retrieving recipes. Server could not be reached.");
            }else{
                this.$store.commit('SET_NOTIFICATION', "Error retrieving recipes. Request could not be created.");
            }
            });
        }
    },
    created(){
        this.retrieveRecipes();
    }
}
</script>

<style>

</style>