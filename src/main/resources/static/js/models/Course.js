export default class Course {
    constructor(id, courseName, courseDesc, coursePic, city, address, price, keyWords) {
        this.id = id
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.coursePic = coursePic;

        this.city = city
        this.address = address
        this.price = price
        this.keyWords = keyWords
    }

    static createCourse(id, name, desc, picture){
        return new Course(id, name, desc, picture)
    }

}