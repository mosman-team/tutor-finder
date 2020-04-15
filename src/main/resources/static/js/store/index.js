import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

import { auth } from './auth.module';
import {teacherCourse} from "./teacher-course.module";
import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex);

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
        auth:auth,
        teacherCourse:teacherCourse
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
        key: 'vuex_tc',
        paths: ['teacherCourse']
    })]
});