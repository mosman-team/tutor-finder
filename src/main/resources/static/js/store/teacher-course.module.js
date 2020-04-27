import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {displaySnackbar} from "../services/helper-functions";


const API_URL = 'http://localhost:8080/teachers';

export const teacherCourse = {

    state : {
        currentCourse : new Course(null, '',
            '', '', '', '', '', null)
    },
    actions : {
        addCourseAction({commit, dispatch}, formData){
            axios.post(API_URL + "/courses", formData,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(
                response => {
                    commit('updateCourseMutation', response.data)
                    displaySnackbar(dispatch, {text : 'Course created successfully!'})
                }, error =>{
                    displaySnackbar(dispatch, {color : 'error',text : 'Some fields are invalid!'})
                }
            );
        },
        updateCourseAction({commit, dispatch}, data){
            axios.put(API_URL+ "/courses/"+data.courseId, data.formData,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(
                response => {
                    commit('updateCourseMutation', response.data)
                    displaySnackbar(dispatch, {text : 'Course updated successfully!'})
                }, error =>{
                    displaySnackbar(dispatch, {color : 'error',text : 'Some fields are invalid!'})
                }
            );
        },
        // info
        addInfoAction({commit, dispatch}, data){

            axios.post(API_URL + "/courses/" + data.courseId, data.info,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    displaySnackbar(dispatch, {text: 'Course info submitted successfully!'});
                },
                error => {
                    displaySnackbar(dispatch, {color: 'red', text: 'Some error occurred!'});
                }
            );
        },
        // get full course
        getCourseDataAction({commit, dispatch}, id){

            axios.get(API_URL + "/courses/" + id, {
                headers: {
                    "Authorization" : authHeader().Authorization,
                }
            }).then(
                response => {
                    commit('setCurrentCourseMutation', response.data);
                },
                error => {
                }
            )

        }
    },
    mutations : {
        updateCourseMutation(state, course){
            state.currentCourse = course;
        },
        emptyCourseMutation(state){
            state.currentCourse = new Course(null, '',
                '', '', '', '', '', null);
        },
        setCourseImgNameMutation(state, imgName){
            state.currentCourse.coursePic = imgName;
        },
        setCurrentCourseMutation(state,data){
            state.currentCourse = data
        },
        // info
        removeFromKeyWordsMutation(state, keyWord){
            const index = state.info.keyWords.indexOf(keyWord);
            if (index > -1) {
                state.info.keyWords.splice(index, 1);
            }
        },
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
