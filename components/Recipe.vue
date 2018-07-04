<template>
    <nuxt-link :to="'/recipes/' + id">
        <article class="recipe">
            <div class="thumbnaill" v-bind:style="{backgroundImage: 'url(' + thumbnail + ')'}"></div>
            <h3>{{ title }}</h3>
            <p>{{ previewText }}</p>
        </article>
    </nuxt-link>
</template>

<script>
export default {
    props: ['thumbnail', 'title', 'previewText', 'id']
}
</script>

<style scoped>
.recipe {
    box-sizing: border-box;
    width: 280px;
    padding: 8px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 2px #aaa;
    margin: 1rem;
}

.thumbnaill {
    background-position: center;
    background-size: cover;

    width: 100%;
    height: 200px;
}
</style>