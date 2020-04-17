


/*
function successCallback(msg, store) {
    return response => {
        store.dispatch('setSnackbarAction', {text : msg});
    };
}
function failureCallback(msg, store) {
    return error => {
        store.dispatch('setSnackbarAction', {color : 'error',text : msg});
    };
}
*/

function successCallback(commit,commitName) {
    return response => {
        console.log(response.status)
        commit(commitName, response.data)
        return Promise.resolve(response);
    };
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
