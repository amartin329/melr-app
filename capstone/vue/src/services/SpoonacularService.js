import axios from 'axios';
export default{
    getResults(){
        try{
            return axios.get('/api/get-results');
        }catch(error){
            alert("Error: No response from Web API.")
        }
        
    },

    searchResults(search){
        try{
            return axios.get(`/ingredients/search/${search}`);
        }catch(error){
            alert("Error: No response from Web API.")
        }
        
    },

    getIngredientInformation(id, unit, amount){
        try{
            return axios.get(`/ingredients/${id}/information/${unit}/${amount}`);
        }catch(error){
            alert("Error: No response from Web API.")
        }
       
    },

    handleError(error){

    }

}
