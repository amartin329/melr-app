import axios from 'axios';
export default{
    getResults(){
        return axios.get('/api/get-results');
    },

    searchResults(search){
        return axios.get(`/ingredients/search/${search}`);
    },

    getIngredientInformation(id, unit, amount){
        return axios.get(`/ingredients/${id}/information/${unit}/${amount}`);
    }

}
