import axios from "axios";
import authHeader from "../services/auth-header";
import Course from "../models/Course";

import {displaySnackbar} from "../services/helper-functions";

const API_URL = 'http://localhost:8080';

export const course = {

    state : {
        courses : [],
        course : new Course(null, '',
            '', '', '', '', '', null),
        enrolledCoursesIds : []
    },

    actions : {
        // ----------------TEACHER-------------
        fetchCoursesAction({commit, dispatch}){
            axios.get(API_URL + "/teachers/courses",
                {
                    headers: {"Authorization" : authHeader().Authorization,}
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
            axios.delete(API_URL +"/teachers/courses/"+data.courseId,
                {
                    headers: {"Authorization" : authHeader().Authorization,}
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
        fetchCourseAction({commit,dispatch}, id){
            axios.get(API_URL + "/teachers/courses/" + id, {
                headers: {"Authorization" : authHeader().Authorization}
            }).then(
                response => {
                    console.log(response.data)
                    commit('setCourseAction', response.data)
                }, error =>{
                    displaySnackbar(dispatch, {color : 'error',text : 'Something went wrong!'})
                }
            )
            /*const tempCourse = new Course(null,'Programming for beginners',
                'Lorem ipsum dolor sit amet, consectetur adipiscing ' +
                'elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                '294cccdb-fe3b-4cb3-a014-debe191c9a10.tmath.jpg',
                'Almaty',
                '8939 Leeton Ridge Court Akron, OH 44312',
                '3000 $',
                'kz',
                ['if-else', 'loop', 'variables', 'object', 'class', 'inheritance', 'private'])
            */
        },
        //----------------STUDENT-------------
        fetchAllCoursesAction({commit, dispatch}){
            axios.get(API_URL + "/courses",
                {
                    headers: {"Authorization" : authHeader().Authorization,}
                }
            ).then(
                response => {
                    commit('fetchAllCoursesMutation', response.data)
                },
                error =>{
                    displaySnackbar(dispatch,{color : 'error',text : 'Could not fetch all courses!'})
                }
            )
        },
        fetchStudentCourseAction({commit,dispatch}, id){
            axios.get(API_URL + "/courses/" + id, {
                headers: {"Authorization" : authHeader().Authorization}
            }).then(
                response => {
                    commit('setCourseAction', response.data)
                }, error =>{
                    displaySnackbar(dispatch, {color : 'error',text : 'Something went wrong!'})
                }
            )
        },
        fetchEnrolledCoursesIds({commit}){
            axios.get(API_URL + "/courses/enrolledCourses", {
                headers: {"Authorization" : authHeader().Authorization}
            }).then(
                response => {
                    commit('setEnrolledCoursesIds', response.data)
                }, error =>{}
            )
        },
        enrollCourseAction({commit,dispatch}, id){
            axios.put(API_URL + "/courses/" + id,{}, {
                headers: {"Authorization" : authHeader().Authorization}
            }).then(
                response => {
                    commit('setEnrolledCoursesIds', response.data)
                }, error =>{
                    displaySnackbar(dispatch, {color : 'error',text : 'Something went wrong!'})
                }
            )
        },
        fetchStudentCourses({commit, dispatch}){
            axios.get(API_URL + "/courses/student",
                {
                    headers: {"Authorization" : authHeader().Authorization,}
                }
            ).then(
                response => {
                    commit('fetchAllCoursesMutation', response.data)
                },
                error =>{
                    displaySnackbar(dispatch,{color : 'error',text : 'Could not fetch your courses!'})
                }
            )
        }

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
        },
        setCourseAction(state, course){
            state.course = course
        },
        fetchAllCoursesMutation(state, courses){
            state.courses = courses
        },
        setEnrolledCoursesIds(state, data){
            state.enrolledCoursesIds = data
        }

    },
    getters : {
        getCourses(state){
            return state.courses
        },
        getCourse(state){
            return state.course
        },
        getEnrolledCoursesIds(state){
            return state.enrolledCoursesIds
        }
    }

}