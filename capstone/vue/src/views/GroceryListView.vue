<template>
  <h1>{{ this.$store.state.user.username}}'s Grocery List</h1>
  <grocery-list/>
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