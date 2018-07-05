<template>
    <section class="single-recipe">
        <h1>{{ recipe.title }}</h1>
        <div>
            <img class="recipe-image" :src="recipe.thumbnail" :alt="recipe.title">
        </div>
        <p>{{ recipe.description }}</p>
        <nuxt-link to="../recipes">Recipes page</nuxt-link>
    </section>
</template>

<script>
export default {
    asyncData(context) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve({
                    recipe: [
                        {
                            id: "1",
                            thumbnail: "https://drop.ndtv.com/albums/COOKS/chicken-dinner/chickendinner_640x480.jpg",
                            title: "Fish meal",
                            description: "you need fish and garlic to make this into a great meal",
                            previewText: "it is a italic fish with olives and tomatoes"
                        },
                        {
                            id: "2",
                            thumbnail: "https://cdn-image.foodandwine.com/sites/default/files/styles/4_3_horizontal_-_1200x900/public/2018-food-trends-ft-blog1117.jpg",
                            title: "Cramberry cakes",
                            description: "you need cramberries and cake to make this into a great meal",
                            previewText: "really unhealthy but o so delicious"
                        },
                        {
                            id: "3",
                            thumbnail: "https://www.samaa.tv/wp-content/uploads/2017/02/PAK-FOOD.jpg",
                            title: "Kebab schotel",
                            description: "you need kebab and onion to make this into a great meal",
                            previewText: "meat from a fat pork"
                        }
                    ].find(el => el.id === context.params.id)
                })
            }, 1500)
        })
    }
}
</script>


<style scoped>
.single-recipe {
    display: flex;
    flex-flow: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    padding: 1rem;
}

.recipe-image{
    width: 100%;
}
</style>