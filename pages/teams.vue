<template>
    <div class="container">
        <h1>Teams:</h1>
        <ul>
            <li v-for="(team, index) in teams" :key="index">
                <nuxt-link :to="{ name: 'teamrunners', params: { team: team } }">{{ team.name }}</nuxt-link>
            </li>
        </ul>
        <nuxt-link to="/"><p>Back to home page</p></nuxt-link>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        layout: 'default',
        async asyncData({ req, params }) {
            return axios.get('http://185.95.31.64:4567/teams')
                .then((res) => {
                    return { teams: res.data }
                })
        }
    }
</script>