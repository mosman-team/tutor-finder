import axios from "axios";
import authHeader from "../services/auth-header";

const API_URL = 'http://localhost:8080/teachers/';

export const teacherCourse = {

    state : {

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
            ).then(console.log).catch(console.log);
        }
    },
    mutations : {

    }
}