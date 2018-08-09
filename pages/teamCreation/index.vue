<!--
this page is for the players to create their own team.
Functionalities:
- add runner
- remove runner
- view budget
- view possible runners
-->
<template>
    <div id="container">  
        <h1>{{$store.state.someText}}</h1>
        <div class="runners">
            <li v-for="(runner, index) in runners" :key="index">
                <p>{{runner.firstName}} {{runner.lastName}}: </p><b>{{runner.price}}</b> 
                <button v-if="!runner.bought" v-on:click="addRunner(runner.runnerId)">voeg toe </button>
            </li>
        </div>
        <div class="runners">
            <h2>Budget: {{ $store.state.budget }}</h2>   
            <RunnerTable
                :runners = teamRunners
                :canEdit = true
                :removeRunnerFunc = removeRunner
            />
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import RunnerTable from '@/components/RunnerTable'

var userId = 1;
var startBudget = 500;

function GetRunnersValue(runners){
    var value = 0;
    runners.forEach(function(item, index){
        value += item.price;
    });
    return value;
}

export default {
    computed: {
        teamRunners(){
            return this.$store.getters.teamRunners;
        }
    },
    components: {
        RunnerTable
    },
    /*async asyncData({ query, error }, callback) {
        let [runnersRes, teamRunnersRes] = await Promise.all([
            axios.get('http://185.95.31.64:4567/runners'),
            axios.get('http://185.95.31.64:4567/users/'+ userId +'/team')
        ])
        callback(null, {
            runners: runnersRes.data,
            teamRunners: teamRunnersRes.data,
            budget: 1000
        })
    },*/
    asyncData({ req, params }) {
    return axios.get('http://185.95.31.64:4567/runners')
      .then((res) => {
        return { runners: res.data }
      })
    },
    methods: {
        addRunner(runnerId){//array id + 1
            var id = JSON.stringify(runnerId);
            var runner = this.runners[runnerId - 1];

            runner.bought = true;

            axios.put('http://185.95.31.64:4567/users/'+ userId +'/team/' + id);
            this.$store.commit('addRunner', runner);
        },
        removeRunner(runnerId){//database ID
            var id = JSON.stringify(runnerId);
            var runner;
            this.runners.forEach(function(item, index){
                if(JSON.stringify(item.runnerId) == id){
                    runner = item;
                    return;
                }
            });        

            var teamRID = 0;
            this.teamRunners.forEach(function(item, index){
                if(JSON.stringify(item.runnerId) == id){
                    teamRID = index;
                    return;
                }
            }); 
               
            runner.bought = false;

            axios.delete('http://185.95.31.64:4567/users/'+ userId +'/team/' + id);
            console.log(runner.runnerId + '/' + teamRID);
            this.$store.commit('removeRunner', teamRID);
        },
        getRunner(id){//id = (stringified) database ID
            this.runners.forEach(function(item, index){
                if(JSON.stringify(item.runnerId) == id){
                    alert('found it, price: ' + item.price);
                    return item;
                }
            });
        }
    },
    created() {
        this.$store.commit('initUserId', userId);
        this.$store.commit('initBudget', startBudget);
        this.$store.dispatch('initRunners')
    }
}
</script>

<style scoped>
#container{
    display: flex;
    flex-flow: row;
    justify-content: space-around;
    align-items: start;
}

.runners{
    width: 300px;
    margin: 1rem;
    padding: 1rem 1rem;
    border: 1px solid rgb(177, 177, 177);
}

li{
    list-style: none;
}
</style>


