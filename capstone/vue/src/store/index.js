import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import mealPlanService from '../services/MealPlanService';
import mealService from '../services/MealService';
import recipeService from '../services/RecipeService';
import spoonacularService from '../services/SpoonacularService';


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
      currentMealPlan: 
      {
      },
      currentMeal: {},
      currentRecipe: {},
      currentIngredient:{},
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

      SET_MEALS(state, mealList){
        state.meals = mealList;
      },

      SET_RECIPES(state, recipeList){
        state.recipes = recipeList;
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
        mealPlanService.addMealPlan(plan).then(response =>{
          if(response.status === 201){
            const newMealPlan = response.data;
            commit('ADD_MEALPLAN', newMealPlan);
            return newMealPlan;
          }
        })
      },

      addMealToPlan({commit}, {mealplanId, mealId}){

        mealPlanService.addMealToMealPlan(mealplanId, mealId).then(response =>{
         
          if(response.status === 201){
            this.getMealPlans();
          }
        })
            }

    }
  });
  return store;
}
