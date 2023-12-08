<template>
  <h1>My meal plan</h1>
  <h2>{{ name }}</h2>
  <div class="meals">
    <div class="meal" v-for="meal in meals" v-bind:key="meal.id">
        <!-- NOTE: v-on | view meal details on click? hover (for preview)? -->
        <h3>{{ meal.name }}</h3>
    </div>
    
  </div>
</template>

<script>
export default {

    props: ['name', 'meals']
}
</script>

<style>

</style>