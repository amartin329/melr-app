<template>
  <h1>{{ this.$store.state.user.username}}'s Grocery List</h1>
  <grocery-list/>
  <p>UNDER CONSTRUCTION: This will contain all ingredients required for a user's meal plan schedule</p>
</template>

<script>
import GroceryList from "../components/GroceryList.vue"

export default 
  {
    components: { GroceryList },
}
</script>

<style>

</style>