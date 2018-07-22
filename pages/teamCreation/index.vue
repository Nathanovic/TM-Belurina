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

function GetRunnersValue(runners){
    var value = 0;
    runners.forEach(function(item, index){
        value += item.price;
    });
    return value;
}

export default {
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

            this.budget += runner.price;
            this.teamRunners.push(runner);
            runner.bought = true;

            axios.put('http://185.95.31.64:4567/users/'+ userId +'/team/' + id);
            $context.store.commit('increment', runner.price);
        },
        removeRunner(runnerId){//database ID
            var id = JSON.stringify(runnerId);
            var runner;
            this.runners.forEach(function(item, index){
                if(JSON.stringify(item.runnerId) == id){
                    runner = item;
                }
            });
            var teamRID = this.teamRunners.indexOf(runner);            
            
            this.budget -= runner.price;
            this.teamRunners.splice(teamRID, 1);
            runner.bought = false;

            axios.delete('http://185.95.31.64:4567/users/'+ userId +'/team/' + id);
            $context.store.commit('increment', -runner.price);
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
    fetch({ store, params }) {
        return axios.get('http://185.95.31.64:4567/users/'+ userId +'/team')
        .then((res) => {
            //store.commit('init', GetRunnersValue(res.data))//???

            var budget = 1000;
            res.data.forEach(function(item, index){
                budget += item.price;
            });
        })
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
    margin: 1rem;
    padding: 1rem 1rem;
    border: 1px solid rgb(177, 177, 177);
}

li{
    list-style: none;
}
</style>


