<template>

    <v-container>

        <div class="searchCourse">
            <v-text-field v-model="search">
                <template v-slot:label>
                    Search <strong>course</strong>
                </template>
            </v-text-field>
            <v-btn class="primary searchBtn" @click="searchForCourse">
                <v-icon style="vertical-align: middle">search</v-icon>
            </v-btn>
        </div>

        <div class="display-1 .font-italic font-weight-light mb-5">Available Courses</div>

        <div class="courses">
            <div v-for="(course, i) in getCourses" :key="i" class="course">
                <student-course
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
        name: "StudentCourses",
        components : {
            StudentCourse
        },
        data() {
            return{
                search: ''
            }
        },
        methods : {
            ...mapActions(['searchCoursesAndFetchAction', 'fetchAllCoursesAction']),
            searchForCourse: function () {

                if (this.search && this.search !== '')
                    this.searchCoursesAndFetchAction(this.search);
                else {
                    this.fetchAllCoursesAction()
                }
            }
        },
        computed: {
            ...mapGetters(['getCourses'])
        },
        created() {
            this.fetchAllCoursesAction()
        }
    }
</script>

<style scoped>
    .searchCourse{
        display: flex;
    }
    .searchBtn{
        margin-top: 12px;
    }

</style>