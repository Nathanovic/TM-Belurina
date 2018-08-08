import Vuex from 'vuex'

const createStore = () => {
    return new Vuex.Store({
        state: {
            budget: 0,
            runners: [],
            teamRunners: []
        },
        mutations: {
            init(state, startValue, teamRunners){
                state.budget = startValue;
                
                teamRunners.foreach(function(item, index){
                    state.budget -= item.price;
                    state.teamRunners.add(item);
                });
            },
            addRunner (state, runner){ 
                state.teamRunners.add(runner);
            }
        }
    })
}

export default createStore
