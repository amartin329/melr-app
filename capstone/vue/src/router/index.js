// NOTE: Edit views may not be needed; could be consolidated with Details views.

import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import RecipeListView from '../views/RecipeViews/RecipeListView.vue'
import RecipeDetailsView from '../views/RecipeViews/RecipeDetailsView.vue'
import CreateRecipeView from '../views/RecipeViews/CreateRecipeView.vue'
import EditRecipeView from '../views/RecipeViews/EditRecipeView.vue'
import MealListView from '../views/MealViews/MealListView.vue'
import MealDetailsView from '../views/MealViews/MealDetailsView.vue'
import CreateMealView from '../views/MealViews/CreateMealView.vue'
import EditMealView from '../views/MealViews/EditMealView.vue'
import MealPlanListView from '../views/MealPlanViews/MealPlanListView.vue'
import MealPlanDetailsView from '../views/MealPlanViews/MealPlanDetailsView.vue'
import CreateMealPlanView from '../views/MealPlanViews/CreateMealPlanView.vue'
import EditMealPlanView from '../views/MealPlanViews/EditMealPlanView.vue'
import GroceryListView from '../views/GroceryListView.vue'

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/recipes',
    name: 'recipes',
    component: RecipeListView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/recipes/:id',
    name: 'recipe-details',
    component: RecipeDetailsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/recipes/add',
    name: 'create-recipe',
    component: CreateRecipeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/recipes/:id/edit',
    name: 'edit-recipe',
    component: EditRecipeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/meals',
    name: 'meals',
    component: MealListView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/meals/:id',
    name: 'meal-details',
    component: MealDetailsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/meals/add',
    name: 'create-meal',
    component: CreateMealView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/meals/:id/edit',
    name: 'edit-meal',
    component: EditMealView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/plans',
    name: 'plans',
    component: MealPlanListView,
    meta:{
      requiresAuth: true
    }
  },
  {
    path: '/plans/:id',
    name: 'plan-details',
    component: MealPlanDetailsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/plans/add',
    name: 'create-plan',
    component: CreateMealPlanView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/plans/:id/edit',
    name: 'edit-plan',
    component: EditMealPlanView,
    meta:{
      requiresAuth: true
    }
  },
  {
    path: '/grocery-list',
    name: 'grocery-list',
    component: GroceryListView,
    meta: {
      requiresAuth: true
    }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }

  store.commit('SET_FORM_VISIBLE')
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
