function successCallback(commit,commitName) {
    return response => {
        commit(commitName, response.data)
        return Promise.resolve(response);
    };
}



export default function UserImgOrDefault(user) {
    if (user && user.img){
        return '/img/' + user.img
    }else if (user.roles[0].name === 'ROLE_TEACHER'){
        return '/static/img/teacher.png'
    }else {
        return '/static/img/student.png'
    }
}

function failureCallback() {
    return error => {
        return Promise.reject(error);
    };
}
function displaySnackbar(dispatch, params) {
    dispatch('setSnackbarAction', params,
        { root: true })
}
export {successCallback, failureCallback, displaySnackbar}