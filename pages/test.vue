<template>
    <div id="container">        
        <button v-on:click="show = !show">
            Toggle text
        </button>
        <transition name="fade">
            <p v-if="show">visible text: {{show}} </p>
        </transition><br>
        <button v-on:click="aFunction()">
            show alert
        </button>
        <button @click="$store.commit('increment', 2)">
            {{ $store.state.budget }}
        </button>
    </div>
</template>

<script>
import axios from 'axios'
if(process.browser){
    window.onNuxtReady((app) => {
        alert('nuxt is ready');
    })
}

export default {
    transition: 'bounce',
    data (){
        return {
            show: true
        }
    },
    methods: {
        aFunction(){
            alert("aFunction has been called!");
        }
    }/*,
    fetch ({ store, params }) {
        return axios.get('http://185.95.31.64:4567/runners')
        .then((res) => {
            store.commit('init', res.data)//???
        })
    }*/
}
</script>

<style scoped>
.bounce-enter-active {
	animation: bounce-in .8s;
}
.bounce-leave-active, .bounce-leave {
	animation: bounce-out .5s;
}
@keyframes bounce-in {
	0% { transform: scale(0) }
	50% { transform: scale(1.2) }
	100% { transform: scale(1) }
}
@keyframes bounce-out {
	0% { transform: scale(1) }
	50% { transform: scale(1.2) }
	100% { transform: scale(0) }
}

.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to {
    opacity: 0;
}
</style>
