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
    import rs from '~/lib/restservice';

    export default {
        layout: 'default',
        async asyncData ({ params }) {
            return rs.teams().then((data) => {
                return {
                    teams: data
                }
            });
        }
    }
</script>