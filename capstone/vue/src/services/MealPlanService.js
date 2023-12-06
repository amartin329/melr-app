import axios from 'axios';

export default{
    getMealPlans(){
        return axios.get('/mealplans')
    },

    getMealPlanById(id){
        return axios.get(`/mealplans/${id}`)
    },

    addMealPlan(mealPlan){
        return axios.post('/mealplans', mealPlan)
    },

    updateRecipe(mealPlan){
        return axios.put(`/mealplans/${mealPlan.id}`, mealPlan)
    },

    deleteRecipe(id){
        return axios.delete`/mealplans/${id}`
    }
}