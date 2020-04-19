import axios from "axios";
import authHeader from "../services/auth-header";

import {displaySnackbar} from "../services/helper-functions";
import Info from "../models/Info";

const API_URL = 'http://localhost:8080/courses/';

export const courseInfo = {

    state : {
        info : new Info(null, '', '', '', null),
    },
    actions : {
        addInfoAction({commit, dispatch}, data){
            console.log(data);
            console.log(API_URL + data.courseId);

            axios.post(API_URL + data.courseId, data.info,
                {
                    headers: {
                        "Authorization" : authHeader().Authorization,
                    }
                }
            ).then(
                response => {
                    commit('addInfoMutation', response.data);
                    displaySnackbar(dispatch, {text: 'Course info submitted successfully!'});
                },
                error => {
                    displaySnackbar(dispatch, {color: 'red', text: 'Some error occurred!'});
                }
            );
        },

    },
    mutations : {
        addInfoMutation(state, info){
            /*console.log(info);*/
            state.info = info;
        },
        removeFromKeyWords(state, keyWord){
            const index = state.info.keyWords.indexOf(keyWord);
            if (index > -1) {
                state.info.keyWords.splice(index, 1);
            }

        },
        emptyCourseInfoMutation(state){
            state.info = new Info(null, '', '', '', null);
        }
    },
    getters : {
        getInfo(state){
            return state.info;
        }
    }
}
