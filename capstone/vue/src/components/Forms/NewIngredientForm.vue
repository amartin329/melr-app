    <template>
  <div>
    <div v-for="ingredient in recipeIngredients" v-bind:key="ingredient.id">
      <!-- {{ ingredient }} -->
      <div class="ingredient-info">
        <p>
          {{ ingredient.amount }} {{ ingredient.unit }} of {{ ingredient.name }}
        </p>
        <div class="ingredient-nutrition">
          <p>
            {{ ingredient.nutritionDTO.calories }} calories |
            {{ ingredient.nutritionDTO.protein }}g protein |
            {{ ingredient.nutritionDTO.carbs }}g carbs |
            {{ ingredient.nutritionDTO.fat }}g fat
          </p>
        </div>
      </div>
    </div>
    <div
      class="pending-ingredients"
      v-if="this.$store.state.pendingIngredients.length > 0"
    >
      <div
        class="pending-ingredient"
        v-for="ingredient in this.$store.state.pendingIngredients"
        v-bind:key="ingredient.id">
        <p v-if="ingredient.name">{{ ingredient.name }}</p>
        <p v-else-if="ingredient.ingName">{{ ingredient.ingName }}</p>
        <label :for="ingredient.id + '-amount'"
          >Amount:
          <input
            type="number"
            :id="ingredient.id + '-amount'"
            v-model="ingredient.amount"
          />
        </label>

        <label :for="ingredient.id + '-unit'"
          >Unit:
          <select :id="ingredient.id + '-unit'" v-model="this.unit">
            <option>------</option>
            <option>ounces</option>
            <option>pounds</option>
            <option>grams</option>
            <option>fluid ounces</option>
            <option>teaspoons</option>
            <option>tablespoons</option>
            <option>cups</option>
            <option>pieces</option>
            <option>slices</option>
            <option>servings</option>
          </select>
        </label>
        <p></p>
        <button v-if="ingredient.name"
          v-on:click.prevent="
            getIngredientInformation(
              ingredient.id,
              ingredient.amount,
              this.unit
            )
          "
        >
        
          Add to Recipe
        </button>
        <button v-else v-on:click.prevent="addIngredientToRecipe(ingredient, this.unit)">Add to Recipe</button>
        <button v-on:click.prevent="removeIngredientFromPending(ingredient.id)">
          Cancel
        </button>
      </div>
    </div>
    <div v-else class="form-group">
      <label for="userSearch">Search for ingredient:</label>
      <input
        type="text"
        v-model="this.userSearch"
        id="userSearch"
        autocomplete="off"
        class="form-control"
        placeholder="Search for an ingredient:"
      />
      <div class="autocomplete-dropdown" v-if="!this.apiFail">
        <div
          v-for="(result, name) in userResults"
          v-bind:key="name"
          id="ingredientList"
          class="autocomplete-dropdown"
          v-on:click.prevent="addIngredientToPending(result)"
        >
          <img v-bind:src="getImage(result.image)" />
          {{ result.name }}
        </div>
      </div>
      <div v-else class="autocomplete-dropdown">
        <div
        v-for="ingredient in this.$store.state.ingredients"
        v-bind:key="ingredient.ingId"
        id="ingredientList"
        class="autocomplete-dropdown"
        v-on:click.prevent="addIngredientToPending(ingredient)">
        {{ ingredient.ingName }}
      
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

      <button v-if="!this.apiFail" v-on:click.prevent="searchResults">
        Search for ingredient
      </button>

      <!-- <button v-on:click="getIngredientInformation(result)">Add Ingredient to Recipe</button> -->
    </div>
  </div>
</template>
      
      <script>
import RecipeService from '../../services/RecipeService';
import SpoonacularService from "../../services/SpoonacularService";
export default {
  // props: ['ingredients'],
  data() {
    return {
      ingredientDTO:{
        ingName: "",
        quantity: 0,
        msmId: 0

      },
      ingredients: [...this.$store.state.ingredients],
      userSearch: "",
      // pendingIngredients: [],
      userResults: [],
      result: {
        id: 0,
        image: "",
        name: "",
        amount: 0,
        unit: "",
      },

      recipeIngredients: [],
      ingredient: {
        amount: 0,
        id: 0,
        image: "",
        name: "",
        nutritionDTO: {
          nutritionId: 0,
          calories: 0,
          protein: 0,
          fat: 0,
        },
        possibleUnits: [],
        unit: "",
        unitLong: "",
        unitShort: "",
      },
      msmId: 0,
      unit: "",
      apiFail: false
    };
  },
  methods: {
    getCurrentRecipe(recipeId){
      this.$store.dispatch('getRecipeById', recipeId)
    },

    getIngredients(){
      this.$store.dispatch('getIngredients')
    },

    getResults() {
      SpoonacularService.getResults().then((response) => {
        console.warn(response);
        this.results = response.data;
        alert("Error retrieving ingredients from Web API.");
      });
    },
    searchResults() {
      SpoonacularService.searchResults(this.userSearch)
        .then((response) => {
          console.log("Error retrieving ingredients from Web API.");
          console.warn(response);
          if (response.status == 200) {
            this.userResults = response.data;
          } else {
            window.alert("Error retrieving ingredients from Web API.");
          }
        })
        .catch((error) => {
          window.alert("Error connecting to Web API");
          this.apiFail = true;
          // ingredientService.getIngredients().then(response =>{
          //   window.alert(response.data);
          // })
        });
    },
    getImage(image) {
      return "https://spoonacular.com/cdn/ingredients_100x100/" + image;
    },
    // addIngredient(ingredient){
    //   let newIngredient = this.getIngredientInformation(ingredient.id, ingredient.unit, ingredient.amount)
    //   this.recipeIngredients.push(newIngredient);
    // },

    getIngredientInformation(id, amount, unit) {
      this.unitToId(unit);
      console.warn(this.msmId);
      console.log(id, amount, unit);
      
      SpoonacularService.getIngredientInformation(id, unit, amount)
        .then((response) => {
          console.warn(response);
          if (response.status === 200) {
            console.warn(response.data)
            //Create ingredient
            this.ingredientDTO.msmId = this.msmId;
            this.ingredientDTO.ingName = response.data.name;
            this.ingredientDTO.quantity = response.data.amount;
            this.$store.dispatch('createIngredient', this.ingredientDTO).then(createResponse => {
              this.$store.dispatch('addIngredientToRecipe', {
                recipeId: this.$store.state.currentRecipe.recipeId,
                ingredientId: createResponse.ingId,
                msmId: this.ingredientDTO.msmId,
                quantity: this.ingredientDTO.quantity
              });
              this.getCurrentRecipe(this.$store.state.currentRecipe.recipeId)
              
              // this.getCurrentRecipe(this.$store.state.currentRecipe.recipeId);
            }
              //Now, add ingredient to recipe (including unit and quantity)
             
            )
            // this.getCurrentRecipe(this.$store.state.currentRecipe.recipeId);
            this.removeIngredientFromPending(id);
            this.$store.dispatch('getRecipeById', this.$store.state.currentRecipe.recipeId);

          } else {
            alert("Error retrieving ingredients from Web API.");
            this.recipeIngredients.push(this.ingredient);
          }
          this.$store.dispatch('getRecipeById', this.$store.state.currentRecipe.recipeId);

        })
        .catch((error) => {
          window.alert("Error retrieving ingredients from Web API.");
          this.apiFail = true;
        });
    },

    addIngredientToRecipe(ingredient, unit){
      this.unitToId(unit);
      this.$store.dispatch('addIngredientToRecipe', {
                recipeId: this.$store.state.currentRecipe.recipeId,
                ingredientId: ingredient.ingId,
                msmId: this.msmId,
                quantity: ingredient.amount
              });
              this.getCurrentRecipe(this.$store.state.currentRecipe.recipeId);
              this.removeIngredientFromPending(ingredient.id);
              this.$store.dispatch('getRecipeById', this.$store.state.currentRecipe.recipeId);
            },

    addIngredientToPending(ingredient) {
      if (!this.$store.state.pendingIngredients.includes(ingredient)) {
        this.$store.state.pendingIngredients.push(ingredient);
        console.log(this.$store.state.pendingIngredients);
        console.log("Are ya seeing pendingMeals?");
      }
    },
    removeIngredientFromPending(id) {
      this.$store.state.pendingIngredients =
        this.$store.state.pendingIngredients.filter((pendIngredient) => {
          return pendIngredient.id !== id;
        });
    },



            unitToId(unit) {
      switch (unit) {
        case "ounces":
          this.msmId = 1;
          break;
        case "pounds":
          this.msmId = 2;
          break;
        case "grams":
          this.msmId = 3;
          break;
        case "fluid ounces":
          this.msmId = 4;
          break;
        case "teaspoons":
          this.msmId = 5;
          break;
        case "tablespoons":
          this.msmId = 6;
          break;
        case "cups":
          this.msmId = 7;
          break;
        case "pieces":
          this.msmId = 8;
          break;
        case "slices":
          this.msmId = 9;
          break;
        case "servings":
          this.msmId = 11;
          break;
      }
    },
    },

    //So here, we want to add

    

    // getIngredientInformation(this.result.id, this.resul){

    //}
  
  computed: {
    filteredIngredients() {
      let filteredIngredients = [...this.ingredients];
      filteredIngredients = filteredIngredients.filter((ingredient) => {
        if (!this.pendingIngredients.includes(ingredient)) {
          return ingredient.ingName
            .toLowerCase()
            .includes(this.userSearch.toLowerCase());
        }
      });
      return filteredIngredients;
    },
  },
  created() {
    this.getIngredients();
  },
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
  margin: auto;
  max-height: 150px;
  overflow-y: auto;
  background-color: white;
}
.autocomplete-dropdown div {
  padding: 5px;
  cursor: pointer;
}

.autocomplete-dropdown :hover {
  background-color: lightgray;
}

.submit-form {
  background-color: orange;
  width: 80%;
  height: 10vh;
  font-size: 1.5em;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
}

.submit-form:hover {
  background-color: rgb(91, 206, 250);
}

.pending-ingredients {
  display: flex;
  justify-content: center;
  align-items: center;
}

.pending-ingredients > button {
  margin-left: 10px;
}

.pending-ingredient {
  background-color: hotpink;
}
</style>