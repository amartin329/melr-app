import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import mealPlanService from '../services/MealPlanService';
import mealService from '../services/MealService';
import recipeService from '../services/RecipeService';
import spoonacularService from '../services/SpoonacularService';
import IngredientService from '../services/IngredientService';


const NOTIFICATION_TIMEOUT = 5000;

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      mealPlans: [],
      meals: [],
      recipes: [],
      ingredients: [],
      currentMealPlan: {},
      currentMeal: {},
      currentRecipe: {},
      currentIngredient:{},
      pendingIngredients: [],
      recipeIngredients: [],
      schedule:
      {
        sunday:{
          day: "sunday",
          mealplanName: "",
          mealplanId: 1,

        },
        monday:{
          day: "monday",
          mealplanName: "",
          mealplanId: 2
        },
        tuesday:{
          day: "tuesday",
          mealplanName: "",
          mealplanId: 3
        },
        wednesday: {
          day: "wednesday",
          mealplanName: "",
          mealplanId: 4
        },
        thursday: {
          day: "thursday",
          mealplanName: "",
          mealplanId: 5
        },
        friday: {
          day: "friday",
          mealplanName: "",
          mealplanId: 6
        },
        saturday: {
          day: "saturday",
          mealplanName: "",
          mealplanId: 7
        }
      },
      formIsVisible: false
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },

      SET_MEALPLANS(state, mealPlanList){
        state.mealPlans = mealPlanList;
      },

      ADD_MEALPLAN(state, mealPlan){
        state.mealPlans.push(mealPlan);
      },

      ADD_MEAL(state, meal){
        state.meals.push(meal);
      },
      ADD_RECIPE(state, recipe){
        state.recipes.push(recipe);
      },

      ADD_INGREDIENT(state, ingredient){
        state.ingredients.push(ingredient);
      },

      SET_MEALS(state, mealList){
        state.meals = mealList;
      },

      SET_RECIPES(state, recipeList){
        state.recipes = recipeList;
      },

      SET_INGREDIENTS(state, ingredientsList){
        state.ingredients = ingredientsList;
      },

      SET_CURRENTMEALPLAN(state, currentMealPlan){
        state.currentMealPlan = currentMealPlan;
      },

      SET_CURRENTMEAL(state, currentMeal){
        state.currentMeal = currentMeal;
      },

      SET_CURRENTRECIPE(state, currentRecipe){
        state.currentRecipe = currentRecipe;
      },

      UPDATE_SCHEDULE(state, schedule){
        state.schedule = schedule;
      },

      SET_NOTIFICATION(state, notification){
        if(state.notification) {
          this.commit('CLEAR_NOTIFICATION');
        }

        if(typeof notification === 'string'){
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          }
        } else{
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }

        state.notification = notification;

        notification.timer = window.setTimeout(() => {
          this.commit('CLEAR_NOTIIFICATION');
        }, notification.timeout);
      },

      CLEAR_NOTIFICATION(state) {
        if(state.notification && state.notification.timer) {
          window.clearTimeout(state.notification.timer);
        }
        state.notification = null;
      },
      SET_FORM_VISIBLE(){
        this.state.formIsVisible = false;
      }
    },
    actions: {
      // getters
      getMealPlans({commit}){
        mealPlanService.getMealPlans().then(response => {
          const mealPlans = response.data;
          commit('SET_MEALPLANS', mealPlans)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Error retrieving meal plans from server.`)
        })
      },
      getMeals({commit}){
        mealService.getMeals().then(response => {
          const meals = response.data;
          commit('SET_MEALS', meals)
          console.log('COMMIT! YUP')
        }).catch(error => {
          commit('SET_NOTIFICATION', `Error retrieving meals from server.`)
        })
      },

      getRecipes({commit}){
        recipeService.getRecipes().then(response => {
          const recipes = response.data;
          commit('SET_RECIPES', recipes)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Error retrieving recipes from server.`)
        })
      },

      getIngredients({commit}){
        IngredientService.getIngredients().then(response => {
          const ingredients = response.data;
          commit('SET_INGREDIENTS', ingredients)
        }).catch(error =>{
          commit('SET_NOTIFICATION', `Error retrieving recipes from server.`)
        })
      },

      getMealPlanById({commit}, id){
        mealPlanService.getMealPlanById(id).then(response => {
          const responsePlan = response.data;
          commit('SET_CURRENTMEALPLAN', responsePlan)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Whoops!`)
        })
      },

      getMealById({commit}, id){
        mealService.getMealById(id).then(response => {
          const responseMeal = response.data;
          commit('SET_CURRENTMEAL', responseMeal)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Whoops!`)
        })
      },

      getRecipeById({commit}, id){
        recipeService.getRecipeById(id).then(response => {
          const responseRecipe = response.data;
          commit('SET_CURRENTRECIPE', responseRecipe)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Whoops!`)
        })
      },

      createMealPlan({commit}, plan){
        return mealPlanService.addMealPlan(plan).then(response =>{
          if(response.status === 201){
            const newMealPlan = response.data;
            console.log("NEW MEAL PLAN: " + newMealPlan)
            console.log("ID: " + newMealPlan.mealplanId)
            commit('ADD_MEALPLAN', newMealPlan);
            return newMealPlan;
          }
        })
      },

      addMealToPlan({commit}, {mealplanId, mealId}){

        mealPlanService.addMealToMealPlan(mealplanId, mealId).then(response =>{
         
          if(response.status === 201){
            this.getMealPlanById(mealplanId);
          }
        })
          },
      
          
      createMeal({commit}, meal){
        console.log("MEAL " + meal.mealId + meal.mealName) 
        return mealService.addMeal(meal).then(response =>{
          if(response.status === 201){
            const newMeal = response.data;
            commit('ADD_MEAL', newMeal);
            return newMeal;
          }
        })
      },

      createRecipe({commit}, recipe){
        return recipeService.addRecipe(recipe).then(response => {
          if(response.status === 201){
            const newRecipe = response.data;
            commit('ADD_RECIPE', newRecipe);
            return newRecipe;
          }
        })
      },
      createIngredient({commit}, ingredient){
        return IngredientService.addIngredient(ingredient).then(response =>{
          if(response.status===201){
            const newIngredient = response.data;
            commit('ADD_INGREDIENT', newIngredient);
            return newIngredient
          }
        })
      },

      
      addRecipeToMeal({commit}, {mealId, recipeId}){

        mealService.addRecipeToMeal(mealId, recipeId).then(response =>{
          
        })
          },

          removeMealFromPlan({commit}, {mealplanId, mealId}){
            mealPlanService.removeMealFromPlan(mealplanId, mealId).then(response =>{
              // window.alert("I'm in the store")
              return response;
              //Check mealplanId
              // this.actions.getMealPlanById(mealplanId);
            });
          },

          removeRecipeFromMeal({commit}, {mealId, recipeId}){
            mealService.removeRecipeFromMeal(mealId, recipeId).then(response =>{
              return response;
            })
              // window.alert("We're in the store!")
              // this.actions.getMealById(mealId)
              // return response;
   
          },

          removeIngredientFromRecipe({commit}, {recipeId, ingId}){
            recipeService.removeIngredientFromRecipe(recipeId, ingId).then(response =>{
              // window.alert(response.data)
              // this.actions.getRecipeById(recipeId);
            })
          },

          addIngredientToRecipe({commit}, {recipeId, ingredientId, msmId, quantity}){
            recipeService.addIngredientToRecipe(recipeId, ingredientId, msmId, quantity).then(response =>{
              // this.actions.getRecipeById(recipeId);
            })
          },

         

          updateSchedule({commit}, schedule){
            commit('UPDATE_SCHEDULE', schedule)
          },
          

    }
  });
  return store;
}
