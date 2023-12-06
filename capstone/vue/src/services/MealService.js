import axios from 'axios';

export default{
    getMeals(){
        return axios.get('/meals')
    },

    getRecipeById(id){
        return axios.get(`/meals/${id}`)
    },

    addRecipe(meal){
        return axios.post('/meals', meal)
    },

    updateRecipe(meal){
        return axios.put(`/meals/${meal.id}`, meal)
    },

    deleteRecipe(id){
        return axios.delete`/meals/${id}`
    }
}