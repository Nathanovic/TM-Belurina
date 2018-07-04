<template>
    <div class="container">
        <h1>Runners of team {{ teamname }}</h1>
        <ul>
            <li v-for="(runner, index) in runners" :key="index">
                <p>{{runner.firstName}}</p>
            </li>
        </ul>
        <p><nuxt-link to="/">Back to home page</nuxt-link></p>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        layout: 'default',
        asyncData({ req, params }) {
            // We can return a Promise instead of calling the callback
            return axios.get('http://185.95.31.64:4567/teams/' + params.team.teamId + '/runners')
                .then((res) => {
                    return { teamname: params.team.name, runners: res.data }
                })
        }
    }
</script>