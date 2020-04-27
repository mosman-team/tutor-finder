import axios from "axios";
import authHeader from "../services/auth-header";
import {displaySnackbar} from "../services/helper-functions";


const API_URL = 'http://localhost:8080/course';

export const message = {

    state: {
        messages: []
    },
    getters:{
        sortedMessages: state => {
            return (state.messages || []).sort((a, b) => -(a.id - b.id))
        }
    },
    actions: {
        fetchCourseMessagesAction({commit}, id) {
            axios.get(API_URL + '/' + id + '/' + 'messages', {
                headers: {
                    "Authorization" : authHeader().Authorization,
                }
            }).then(
                response => {
                    commit('setMessagesMutation', response.data)
                },
                error => {

                }
            )
        },
        addMessageAction({commit}, data){
            // console.log(data)
            // console.log(API_URL + '/' + data.id + '/' + 'messages')

            axios.post(API_URL + '/' + data.courseId + '/' + 'messages',
                {
                        text : data.text
                },
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
                ).then(
                response => {
                    commit('addMessageMutation', response.data)
                },
                error => {

                }
            )
        },
        updateMessageAction({commit}, data){
            console.log(data)
            console.log(API_URL + '/' + 'messages/' + data.messageId)

            axios.put(API_URL + '/' + 'messages/' + data.messageId,
                {
                    text : data.text
                },
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    console.log(response.data)
                    commit('updateMessageMutation', response.data)
                },
                error => {

                }
            )
        },
        removeMessageAction({commit}, messageId){
            // console.log(API_URL + "/messages/"+ messageId);

            axios.delete(API_URL + "/messages/"+ messageId,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('removeMessageMutation', messageId)
                },
                error => {
                }
            )
        }
    },
    mutations: {
        setMessagesMutation(state, messages){
            state.messages = messages
        },
        addMessageMutation(state, message){
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutation(state, message){
            const updateIndex = state.messages.findIndex(item => item.id === message.id)

            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex + 1)
            ]
        },
        removeMessageMutation(state, messageId){
            const deleteIndex = state.messages.findIndex(item => item.id === messageId)

            if (deleteIndex > -1){
                state.messages = [
                    ...state.messages.slice(0, deleteIndex),
                    ...state.messages.slice(deleteIndex + 1)
                ]
            }
        }
    },


}