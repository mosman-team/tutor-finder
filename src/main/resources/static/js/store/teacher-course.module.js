import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

const API_URL = 'http://localhost:8080/teachers/';

export const teacherCourse = {

    state : {
        currentCourse : new Course(null, '', '', '')
    },
    actions : {
        addCourseAction({commit}, formData){
            axios.post(API_URL+ this.state.auth.user.id +"/courses", formData,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(
                response => {
                    commit('updateCourseMutation', response.data)
                    return Promise.resolve(response.data);
                },
                error => {
                    return Promise.reject(error)
                }
            );
        }
    },
    mutations : {
        updateCourseMutation(state, course){
            state.currentCourse = course;
        },
        courseCreationProcedureFinished(state){
            state.currentCourse = null
            sessionStorage.clear()
        }
    },
    getters : {
        currentCourse(state){
            return state.currentCourse
        }
    }
}