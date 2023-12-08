<template>
  <h1>Meal Details</h1>
  <router-link v-bind:to="{name: 'meals'}">Back to My Meals</router-link>

</template>

<script>
export default {
components: {

},
data(){
    return{
        meal:{
            name: "",
            id: 0,
            recipes: []
        },
    }
}
}
</script>

<style>

</style>