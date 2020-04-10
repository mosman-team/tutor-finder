import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

import { auth } from './auth.module';

Vue.use(Vuex);

let user = JSON.parse(localStorage.getItem('user'));
const API_IRL = 'http://localhost:8080/teachers/{}';

export default new Vuex.Store({
    state : {
      snackbars : []
    },
    actions : {
        setSnackbarAction({commit}, snackbar){
            snackbar.showing = true;
            snackbar.timeout = snackbar.timeout || 6000;
            snackbar.color = snackbar.color || 'success';
            commit('setSnackbarMutation', snackbar)
        }
    },
    mutations : {
        setSnackbarMutation(state, snackbar){
            state.snackbars = state.snackbars.concat(snackbar);
        }
    },
    modules: {
        auth
    }
});