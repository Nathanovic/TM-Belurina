import Vuex from 'vuex'

const createStore = () => {
    return new Vuex.Store({
        state: {
            budget: -1
        },
        mutations: {
            init(state, startValue){
                state.budget = startValue;
            },
            increment (state, newValue){
                state.budget += newValue;
            }
        }
    })
}

export default createStore
