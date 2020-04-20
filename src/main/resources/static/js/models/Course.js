export default class Course {
    constructor(id, name, desc, picture, city, address, price, language , keyWords) {
        this.id = id
        this.courseName = name;
        this.courseDesc = desc;
        this.coursePic = picture;
        this.city = city
        this.address = address
        this.price = price
        this.language = language
        this.keyWords = keyWords
    }
    static createCourse(id, name, desc, picture){
        return new Course(id, name, desc, picture)
    }
}