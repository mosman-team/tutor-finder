export default class User {
    constructor(username, img,email, password) {
        this.username = username;
        this.img = img;
        this.email = email;
        this.password = password;
        this.role = []
    }
    addRole(role){
        this.role.push(role);
    }
}