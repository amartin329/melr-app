import axios from 'axios';

export default{
    getIngredients(){
        return axios.get('/ingredients')
    },

    addIngredient(ingredient){
        return axios.post('/ingredients', ingredient)
    }

    // getRecipeById(id){
    //     return axios.get(`/recipes/${id}`)
    // },

    // addRecipe(recipe){
    //     return axios.post('/recipes', recipe)
    // },

    // updateRecipe(recipe){
    //     return axios.put(`/recipes/${recipe.id}`, recipe)
    // },

    // deleteRecipe(id){
    //     return axios.delete`/recipes/${id}`
    // },

    // addIngredientToRecipe(recipeId, ingredientId, msmId, quantity){
    //     return axios.post(`/recipes/${recipeId}/modify/${ingredientId}`, null,
    //     {params: {msmId, quantity}})
    // }
}