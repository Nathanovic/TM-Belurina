import Vuex from 'vuex'
import axios from 'axios'

const createStore = () => {
    return new Vuex.Store({
        state: {
            budget: 0,
            userId: -1,
            someText: "hoi",
            runners: [],
            teamRunners: []
        }, 
        mutations: {
            initRunners(state, teamRunners){
                state.someText = 'runners: ' + teamRunners.length;
                
                teamRunners.forEach(function(item, index){
                    state.teamRunners.push(item);
                    state.budget -= item.price;
                });
            },
            initUserId(state, userId){
                state.userId = userId;
            },
            initBudget(state, startBudget){
                console.log(' budget: ' + startBudget);
                state.budget = startBudget;
            },
            addRunner (state, runner){ 
                state.budget -= runner.price;
                state.teamRunners.push(runner);
            },
            removeRunner(state, runnerIndex){
                console.log('remove runner ' + runnerIndex);
                state.budget += state.teamRunners[runnerIndex].price; 
                state.teamRunners.splice(runnerIndex, 1);
            }
        },
        getters: {
            teamRunners(state) {
                return state.teamRunners
            }
        },
        actions: {
            initRunners({commit}){
                this.state.someText = 'loading ' + this.state.userId;
                return axios.get('http://185.95.31.64:4567/users/' + this.state.userId + '/team')
                .then((res) => {
                    commit('initRunners', res.data)
                })
            }
        }
    })
}

export default createStore
