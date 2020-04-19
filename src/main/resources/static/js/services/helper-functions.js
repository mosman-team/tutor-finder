function successCallback(commit,commitName) {
    return response => {
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
