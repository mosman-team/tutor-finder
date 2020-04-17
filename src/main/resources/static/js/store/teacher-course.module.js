import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {successCallback, failureCallback} from "../services/helper-functions";

const API_URL = 'http://localhost:8080/teachers/';

export const teacherCourse = {

    state : {
        currentCourse : new Course(null, '', '', ''),
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
                successCallback(commit,'updateCourseMutation'), failureCallback()
            );
        },
        updateCourseAction({commit}, data){
            axios.put(API_URL+ this.state.auth.user.id +"/courses/"+data.courseId, data.formData,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(
                successCallback(commit,'updateCourseMutation'), failureCallback()
            );
        }
    },
    mutations : {
        updateCourseMutation(state, course){
            state.currentCourse = course;
        },
        courseCreationProcedureFinished(state){
            state.currentCourse = new Course(null, '', '', '')
            sessionStorage.clear()
        },
        setCourseImgNameMutation(state, imgName){
            state.currentCourse.coursePic = imgName;
        }
    },
    getters : {
        getCurrentCourse(state){
            return state.currentCourse
        },
        getCourseImgName(state){
            if (state.currentCourse.coursePic){
                let imgFullName = state.currentCourse.coursePic
                let splitName = imgFullName.split(".")
                return splitName[splitName.length  - 2] + '.' + splitName[splitName.length  - 1]
            }
            return null
        }
    }
}
