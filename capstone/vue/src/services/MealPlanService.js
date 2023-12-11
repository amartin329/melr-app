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

    updateMealPlan(mealPlan){
        return axios.put(`/mealplans/${mealPlan.id}`, mealPlan)
    },

    deleteMealPlan(id){
        return axios.delete(`/mealplans/${id}`)
    },

    addMealToMealPlan(mealplanId, mealId){
        console.log('is this even happening??? What are' + mealplanId + " and " + mealId + "?????")
        return axios.post(`/mealplans/${mealplanId}/modify/${mealId}`, null, 
        {params: {mealplanId, mealId}
    });
    }
}