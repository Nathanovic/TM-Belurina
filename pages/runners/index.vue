<template>
  <div class="container">
    <h1>Runners List:</h1>
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
    return axios.get('http://185.95.31.64:4567/runners')
      .then((res) => {
        return { runners: res.data }
      })
    }
}
</script>

<style scoped>
.container {
  width: 70%;
  margin: auto;
  text-align: center;
  padding-top: 100px;
}
ul {
  list-style-type: none;
  padding: 0;
}
ul li {
  border: 1px #ddd solid;
  padding: 20px;
  text-align: left;
}
ul li a {
  color: gray;
}
p {
  font-size: 20px;
}
a {
  color: #41B883;
}
</style>
