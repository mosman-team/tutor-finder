import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {displaySnackbar} from "../services/helper-functions";

const API_URL = 'http://localhost:8080/teachers';

export const course = {

    state : {
        courses : [],

    },
    actions : {
        fetchCoursesAction({commit, dispatch}){
            axios.get(API_URL + "/courses",
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('fetchCoursesMutation', response.data)
                },
                error =>{
                    displaySnackbar(dispatch,{color : 'error',text : 'Could not fetch courses!'})
                }
            )
        },
        deleteCourseAction({commit, dispatch}, data){
            axios.delete(API_URL +"/courses/"+data.courseId,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('deleteCourseMutation', data.courseId)
                    displaySnackbar(dispatch, {color : 'orange', text : 'Course deleted!'})
                }, error =>{
                    displaySnackbar(dispatch, {color : 'error',text : 'Something went wrong!'})
                }
            );
        },
    },
    mutations : {
        fetchCoursesMutation(state, courses){
            state.courses = courses
        },
        deleteCourseMutation(state, courseId){
            const deleteIndex = state.courses.findIndex(item => item.id === courseId)
            if (deleteIndex > -1){
                state.courses = [
                    ...state.courses.slice(0, deleteIndex),
                    ...state.courses.slice(deleteIndex + 1)
                ]
            }
        }
    },
    getters : {
        getCourses(state){
            return state.courses
        }
    }

}