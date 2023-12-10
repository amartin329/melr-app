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
      currentIngredient:{}
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

      SET_CURRENTMEALPLAN(state, currentMealPlan){
        state.currentMealPlan = currentMealPlan;
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
    },
    actions: {
      getMealPlans({commit}){
        mealPlanService.getMealPlans().then(response => {
          const mealPlans = response.data;
          commit('SET_MEALPLANS', mealPlans)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Error retrieving meal plans from server.`)
        })
      },
      getMealPlanById({commit, id}){
        mealPlanService.getMealPlanById(id).then(response => {
          const responsePlan = response.data;
          commit('SET_CURRENTMEALPLAN', responsePlan)
        }).catch(error => {
          commit('SET_NOTIFICATION', `Whoops!`)
        })
      }
    }
  });
  return store;
}
