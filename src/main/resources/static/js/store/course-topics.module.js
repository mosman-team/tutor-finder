import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {successCallback, failureCallback} from "../services/helper-functions";

const API_URL = 'http://localhost:8080/teachers/';

export const courseTopics = {

    state : {
        topics : []
    },
    actions : {
        fetchTopicsAction({commit}){
            let courseId = JSON.parse(sessionStorage.getItem("vuex_tc")).teacherCourse.currentCourse.id
            axios.get(API_URL + this.state.auth.user.id +"/courses/"+courseId+"/topics",
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                successCallback(commit, 'fetchTopicsMutation'),failureCallback()
            )
        },
        addTopicAction({commit}, data){
            axios.post(API_URL+ this.state.auth.user.id +"/courses/"+data.courseId+"/topics",data.topic,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                successCallback(commit, 'addTopicMutation'),failureCallback()
            )
        },
        deleteTopicAction({commit}, data){
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
                    return Promise.resolve(response);
                },
                error => {
                    return Promise.reject(error);
                }
            )
        },
        updateTopicAction({commit}, data){
            axios.put(API_URL+ this.state.auth.user.id +"/courses/"+data.courseId+"/topics/"+data.topic.id,
                data.topic,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                successCallback(commit, 'updateTopicMutation'),failureCallback()
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