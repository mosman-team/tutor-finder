import Vue from 'vue';
import Vuex from 'vuex';

import { auth } from './auth.module';
import {teacherCourse} from "./teacher-course.module";
import {courseTopics} from './course-topics.module';
import {courseInfo} from './course-info.module';
import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex);

export default new Vuex.Store({
    state : {
      snackbars : []
    },
    actions : {
        setSnackbarAction({commit}, snackbar){
            snackbar.showing = true;
            snackbar.timeout = snackbar.timeout || 2000;
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
        teacherCourse:teacherCourse,
        courseTopics:courseTopics,
        courseInfo: courseInfo,
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
        key: 'vuex_tc',
        paths: ['teacherCourse']
    }),
    createPersistedState({
        storage: window.sessionStorage,
        key: 'vuex_ci',
        paths: ['courseInfo']
    })
    ]
});