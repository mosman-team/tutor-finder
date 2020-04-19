import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {displaySnackbar} from "../services/helper-functions";

const API_URL = 'http://localhost:8080/teachers';

export const courseTopics = {

    state : {
        topics : []
    },
    actions : {
        fetchTopicsAction({commit, dispatch}){
            let courseId = JSON.parse(sessionStorage.getItem("vuex_tc")).teacherCourse.currentCourse.id
            axios.get(API_URL +"/courses/"+courseId+"/topics",
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('fetchTopicsMutation', response.data)
                },
                error =>{
                    displaySnackbar(dispatch,{color : 'error',text : 'Could not fetch topics!'})
                }
            )
        },
        addTopicAction({commit, dispatch}, data){
            axios.post(API_URL + "/courses/"+data.courseId+"/topics",data.topic,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('addTopicMutation', response.data)
                    displaySnackbar(dispatch,{text : 'Topic created successfully!'})
                },
                error =>{
                    displaySnackbar(dispatch,{color : 'error',text : 'Some fields are invalid!'})
                }
            )
        },
        deleteTopicAction({commit, dispatch}, data){
            axios.delete(API_URL + "/courses/"+
                data.courseId+"/topics/"+data.topicId,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('deleteTopicMutation', data.topicId)
                    displaySnackbar(dispatch, {color : 'orange',text : 'Topic deleted!'})
                },
                error => {
                    displaySnackbar(dispatch, {color : 'error',text : 'Could not delete Topic!'})
                }
            )
        },
        updateTopicAction({commit, dispatch}, data){
            axios.put(API_URL + "/courses/"+data.courseId+"/topics/"+data.topic.id,
                data.topic,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('updateTopicMutation', response.data)
                    displaySnackbar(dispatch,{text : 'Topic updated successfully!'})
                },
                error =>{
                    displaySnackbar(dispatch,{color : 'error',text : 'Some fields are invalid!'})
                }
            )
        }
    },
    mutations : {
        fetchTopicsMutation(state, topics){
            state.topics = topics;
        },
        addTopicMutation(state, topic){
            state.topics = [
                ...state.topics,
                topic
            ]
        },
        deleteTopicMutation(state,topicId){
            const deleteIndex = state.topics.findIndex(item => item.id === topicId)
            if (deleteIndex > -1){
                state.topics = [
                    ...state.topics.slice(0, deleteIndex),
                    ...state.topics.slice(deleteIndex + 1)
                ]
            }
        },
        updateTopicMutation(state, topic){
            const updateIndex = state.topics.findIndex(item => item.id === topic.id)

            state.topics = [
                ...state.topics.slice(0, updateIndex),
                topic,
                ...state.topics.slice(updateIndex + 1)
            ]
        }
    },
    getters : {
        getCourseTopics(state){
            return state.topics;
        }
    }

}