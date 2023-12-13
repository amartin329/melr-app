<template>
    <div class="mealplancard">
        <h2>{{ mealplan.mealplanName }}</h2>
    </div>
</template>

<script>
export default {

    props: {
        mealplan: { type: Object, required: true }
    }
}
</script>

<style>
.mealplancard {
    text-align: center;
    border: 2px solid black;
    background-color:blueviolet;
    border-radius: 10px;
    margin: 20px;
    padding: 10px;
}
</style>