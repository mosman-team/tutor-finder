import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/auth_views/Login.vue';
import Register from './views/auth_views/Register.vue';
import Profile from "./views/auth_views/Profile.vue";
import BoardStudent from "./views/BoardStudent.vue";
import BoardTeacher from "./views/BoardTeacher.vue";
import AddCourse from "./views/AddCourse.vue";
import SetCourseTopics from "./components/SetCourseTopics.vue";
import TeacherCourses from "./views/teacher/TeacherCourses.vue";
import CourseInfo from "./views/teacher/CourseInfo.vue";
import StudentCourses from "./views/student/StudentCourses.vue";
import StudentCourseInfo from "./views/student/StudentCourseInfo.vue";
import EnrolledCourses from "./views/student/EnrolledCourses.vue";
import UserProfile from "./views/profile/UserProfile.vue";
import FilterCourses from "./views/FilterCourses.vue";
import SelectedTCourses from "./views/student/SelectedTCourses.vue";
Vue.use(Router);

export const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/profile',
            name: 'profile',
            component: Profile
        },
        {
            path: '/student',
            name: 'student',
            component: BoardStudent
        },
        {
            path: '/teacher',
            name: 'teacher',
            component: BoardTeacher
        },
        {
            path : '/add-course',
            name : 'addCourse',
            component : AddCourse,
        },
        {
            path : '/topics',
            name : 'setCourseTopics',
            component : SetCourseTopics,
        },
        {
            path : '/my-courses',
            name : 'TeacherCourses',
            component : TeacherCourses,
        },
        {
            path : '/course-info/:id',
            name : 'courseInfo',
            component : CourseInfo,
            props: true
        },
        {
            path : '/all-courses',
            name : 'StudentCourses',
            component : StudentCourses,
        },
        {
            path : '/all-courses/:id',
            name : 'StudentCourseInfo',
            component : StudentCourseInfo,
            props: true
        },
        {
            path : '/filter-courses',
            name : 'FilterCourses',
            component : FilterCourses,
        },
        {
            path : '/enrolled-courses',
            name : 'EnrolledCourses',
            component : EnrolledCourses,
        },
        {
            path : '/profile/:id?',
            name : 'UserProfile',
            component : UserProfile,
            props: true
        },
        {
            path : '/teacher/:id/:name/courses',
            name : 'SelectedTCourses',
            component : SelectedTCourses,
            props: true
        },

    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register', '/home', '/'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});
