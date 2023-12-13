    <template>
      <h1>TODO: ADD PARAMS! TO SERVICE METHODS</h1>
      <h1>addMealToMealPlan(mealplanId, mealId){
        return axios.post(`/mealplans/${mealplanId}/modify/${mealId}`, null, 
        {params: {mealplanId, mealId}
    });
    },</h1>
        <div>
            <div v-for="ingredient in recipeIngredients"
            v-bind:key="ingredient.id">
                <!-- {{ ingredient }} -->
                <div class="ingredient-info">
                <p> {{ ingredient.amount }} {{ ingredient.unit }} of {{ ingredient.name}}</p>
                <div class="ingredient-nutrition">
                    <p>{{ ingredient.nutritionDTO.calories}} calories
                    | {{ ingredient.nutritionDTO.protein }}g protein
                    | {{ ingredient.nutritionDTO.carbs }}g carbs
                    | {{ ingredient.nutritionDTO.fat }}g fat</p>
                </div>
                </div>

            </div>
        <div class="pending-ingredients" v-if="this.$store.state.pendingIngredients.length>0">
            <div class="pending-ingredient" v-for="ingredient in this.$store.state.pendingIngredients" v-bind:key="ingredient.id">
                <p>{{ ingredient.name }}</p>
                <label :for="ingredient.id + '-amount'">Amount:
                    <input type="number" :id="ingredient.id + '-amount'" 
                    v-model="ingredient.amount"/>
                </label>
                
                <label :for="ingredient.id + '-unit'">Unit:
                    <select :id="ingredient.id + '-unit'"
                    v-model="ingredient.unit">
                    <option>------</option>
                    <option >ounces</option>
                    <option >pounds</option>
                    <option >grams</option>
                    <option >fluid ounces</option>
                    <option >teaspoons</option>
                    <option >tablespoons</option>
                    <option >cups</option>
                    <option >pieces</option>
                    <option >slices</option>
                    <option >servings</option>
                </select>
                </label>
                <p></p>
                <button v-on:click.prevent="getIngredientInformation(ingredient.id, ingredient.amount, ingredient.unit)">Add to Recipe</button>
               <button v-on:click.prevent="removeIngredientFromPending(ingredient.id)">Cancel</button>
            </div>
        </div>
        <div v-else class="form-group">

<label for="userSearch">Search for ingredient:</label>
<input type="text" v-model="this.userSearch" id="userSearch"
autocomplete="off" class="form-control"
placeholder="Search for an ingredient:"/>
<div class="autocomplete-dropdown">
<div v-for="(result, name) in userResults" v-bind:key="name" 
id="ingredientList" class="autocomplete-dropdown"
v-on:click.prevent="addIngredientToPending(result)">
<img v-bind:src="getImage(result.image)">
{{ result.name }}
</div>
</div>

<!-- <label :for="'amount-' + result.name">
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
</label> -->

<button v-on:click.prevent="searchResults">Search for ingredient</button>


<!-- <button v-on:click="getIngredientInformation(result)">Add Ingredient to Recipe</button> -->
</div>
        
          
        </div>
      </template>
      
      <script>
    import SpoonacularService from '../../services/SpoonacularService';
      export default {
        props: ['ingredients'],
        data(){
         return {
          userSearch: "",
          // pendingIngredients: [],
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
          },
          // unitToId:[
          //   {unit: }
          // ]
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
    
          getIngredientInformation(id, amount, unit){
            console.log(id, amount, unit);
            SpoonacularService.getIngredientInformation(id, unit, amount).then(response => {
              this.recipeIngredients.push(response.data);
              console.log(this.recipeIngredients)
              this.removeIngredientFromPending(id);
            })
          },

          addIngredientToPending(ingredient){
            if(!this.$store.state.pendingIngredients.includes(ingredient)){
                this.$store.state.pendingIngredients.push(ingredient);
        console.log(this.$store.state.pendingIngredients)
        console.log('Are ya seeing pendingMeals?')
            }

    },
        removeIngredientFromPending(id){
            this.$store.state.pendingIngredients = this.$store.state.pendingIngredients.filter(pendIngredient => {
                return pendIngredient.id !== id;
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
.recipeForm {
  padding: 10px;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}

label {
  display: inline-block;
  margin-bottom: 0.5rem;
}

.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  margin: auto;
}

textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}

select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}

.autocomplete-dropdown {
  border: 1px solid black;
  width: 80%;
  margin:auto;
  max-height: 150px;
  overflow-y: auto;
  background-color: white;
}
.autocomplete-dropdown div {
  padding: 5px;
  cursor: pointer;
}

.autocomplete-dropdown :hover{
    background-color: lightgray;
}

.submit-form{
    background-color: orange;
    width: 80%;
    height: 10vh;
    font-size: 1.5em;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.submit-form:hover{
    background-color: rgb(91,206,250)
}

.pending-ingredients{
    display:flex;
    justify-content: center;
    align-items: center;
}

.pending-ingredients > button{
    margin-left: 10px;
}

.pending-ingredient{
    background-color: hotpink;
}
      </style>