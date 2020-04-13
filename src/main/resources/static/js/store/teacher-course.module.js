import axios from "axios";
import authHeader from "../services/auth-header";

const API_URL = 'http://localhost:8080/teachers/';

export const teacherCourse = {

    state : {
        currentCourse : null
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
        }
    },
    getters : {
        currentCourse(state){
            return state.currentCourse
        }
    }
}