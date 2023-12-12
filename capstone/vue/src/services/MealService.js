import axios from 'axios';

export default{
    getMeals(){
        return axios.get('/meals')
    },

    getMealById(id){
        return axios.get(`/meals/${id}`)
    },

    addMeal(meal){
        return axios.post('/meals', meal)
    },

    updateMeal(meal){
        return axios.put(`/meals/${meal.id}`, meal)
    },

    deleteMeal(id){
        return axios.delete`/meals/${id}`
    },

    addRecipeToMeal(mealId, recipeId){
        return axios.post(`/meals/${mealId}/modify/${recipeId}`, null, 
        {params: {mealId, recipeId}
    });
    }
}
