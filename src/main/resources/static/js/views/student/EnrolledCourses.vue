<template>

    <v-container>
        <div class="display-1 .font-italic font-weight-light mb-5">Enrolled Courses</div>
        <div class="d-flex flex-wrap">
            <div v-for="(course, i) in getCourses" class="mb-3 mr-3">
                <student-course
                        :key="i"
                        :course="course">
                </student-course>
            </div>
        </div>
    </v-container>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex';
    import StudentCourse from "../../components/StudentCourse.vue";

    export default {
        name: "EnrolledCourses",
        components : {
            StudentCourse
        },
        data() {
            return{

            }
        },
        computed : {
            ...mapGetters(['getCourses'])
        },
        created() {
            this.fetchStudentCourses()
        },
        methods : {
            ...mapActions(['fetchStudentCourses'])
        }
    }
</script>

<style scoped>

</style>