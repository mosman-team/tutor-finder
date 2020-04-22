import AuthService from '../services/auth.service';
import axios from "axios";
import authHeader from "../services/auth-header";
import {displaySnackbar} from "../services/helper-functions";

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
    ? { status: { loggedIn: true }, user: user }
    : { status: { loggedIn: false }, user: null };

const API_URL = 'http://localhost:8080'

export const auth = {
    namespaced: true,
    state: {
        user : user,
        status : { loggedIn : !!user},
        selectedUser : null
    },
    actions: {
        login({ commit }, user) {
            return AuthService.login(user).then(
                user => {
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            );
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        },
        register({ commit }, user) {
            return AuthService.register(user).then(
                response => {
                    commit('registerSuccess');
                    return Promise.resolve(response.data);
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error);
                }
            );
        },
        fetchSelectedUser({commit, dispatch}, id){
            axios.get( API_URL +'/user/'+id, {
                headers: {"Authorization" : authHeader().Authorization,}
            }).then(
                response =>{
                    commit('setSelectedUser', response.data)
                },
                error => {
                    let res =
                        (error.response && error.response.data) ||
                        error.message ||
                        error.toString();
                    displaySnackbar(dispatch, { color: 'error' ,text : res})
                }
            )
        },
        updateUserAction({commit, dispatch}, data){
            axios.put(API_URL + "/user", data.formData,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(
                response => {
                    commit('updateUserMutation', response.data)
                }, error =>{
                    let res =
                        (error.response && error.response.data) ||
                        error.message ||
                        error.toString();
                    displaySnackbar(dispatch, {color : 'error',text : res})
                }
            );

        }
    },
    mutations: {
        loginSuccess(state, user) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        registerSuccess(state) {
            state.status.loggedIn = false;
        },
        registerFailure(state) {
            state.status.loggedIn = false;
        },
        setSelectedUser(state, data){
            state.selectedUser = data
        },
        updateUserMutation(state, data){
            state.user.img = data.img
            let userFromStorage = JSON.parse(localStorage.getItem('user'))
            userFromStorage.img = data.img
            state.user = userFromStorage;
            localStorage.setItem('user', JSON.stringify(userFromStorage))
        }

    },
    getters : {
        getSelectedUser(state){
            return state.selectedUser
        }
    }
};