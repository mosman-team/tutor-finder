import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {successCallback, failureCallback, displaySnackbar} from "../services/helper-functions";

const API_URL = 'http://localhost:8080/teachers/';

export const courseTopics = {

    state : {
        topics : []
    },
    actions : {
        fetchTopicsAction({commit, dispatch}){
            let courseId = JSON.parse(sessionStorage.getItem("vuex_tc")).teacherCourse.currentCourse.id
            axios.get(API_URL + this.state.auth.user.id +"/courses/"+courseId+"/topics",
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
            axios.post(API_URL+ this.state.auth.user.id +"/courses/"+data.courseId+"/topics",data.topic,
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
            axios.delete(API_URL+ this.state.auth.user.id +"/courses/"+
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
            axios.put(API_URL+ this.state.auth.user.id +"/courses/"+data.courseId+"/topics/"+data.topic.id,
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
        },
        swapTopicsAction({commit, dispatch}, data){
            const swapTopics = {
                firstTopicId : data.firstTopicId,
                secondTopicId : data.secondTopicId,
            }
            console.log(swapTopics)
            axios.put(API_URL+ this.state.auth.user.id +"/courses/"+data.courseId+"/topics/",
                swapTopics,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('swapTopicsMutation', swapTopics)
                    displaySnackbar(dispatch,{text : 'Swapped topics successfully!'})
                },
                error => {
                    displaySnackbar(dispatch,{color : 'error',text : 'Something went wrong'})
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
        },
        swapTopicsMutation(state, topicsIds){
            const firstTopicIndex = state.topics.findIndex(item => item.id === topicsIds.firstTopicId)
            const secondTopicIndex = state.topics.findIndex(item => item.id === topicsIds.secondTopicId)

            console.log('firstTopicIndex :' + firstTopicIndex)
            console.log('secondTopicIndex :' + secondTopicIndex)

            let first = firstTopicIndex, second = secondTopicIndex;
            if (firstTopicIndex > secondTopicIndex){
                first = secondTopicIndex;
                second = firstTopicIndex;
            }

            const firstTopic = state.topics[first];
            const secondTopic = state.topics[second];

            console.log('first :' + first)
            console.log('second :' + second)
            console.log('firstTopic')
            console.log(firstTopic)
            console.log('secondTopic')
            console.log(secondTopic)

            state.topics = [
                ...state.topics.slice(0, first),
                secondTopic,
                ...state.topics.slice(first + 1, second),
                firstTopic,
                ...state.topics.slice(second + 1)
            ]
            console.log(state.topics)
        }
    },
    getters : {
        getCourseTopics(state){
            return state.topics;
        }
    }

}