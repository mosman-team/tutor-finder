<template>
    <div>
        <v-container>
            <div class="display-1 .font-italic font-weight-light mb-5">My courses</div>

                <div class="courses">
                    <div v-for="(course, i) in getCourses" :key="i" class="course">
                        <teacher-course
                                :index = i
                                :course="course"
                                :deleteCourseAction="deleteCourseAction"
                                :editCourse="editCourse">
                        </teacher-course>
                    </div>
                </div>

        </v-container>
    </div>
</template>

<script>

    import TeacherCourse from '../../components/TeacherCourse.vue'
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import {router} from "../../router";
    export default {
        name: "MyCourses",
        components : {
            TeacherCourse
        },
        created() {
            this.fetchCoursesAction()
        },
        computed : mapGetters(['getCourses']),
        methods : {
            ...mapActions(['fetchCoursesAction', 'deleteCourseAction', 'getCourseDataAction']),
            ...mapMutations(['setCurrentCourseMutation']),

            editCourse(index){
                this.getCourseDataAction(this.getCourses[index].id);
                router.push("/add-course")
            },
            viewCourse(){
                console.log(this.getCourses)
            }
        }
    }
</script>

<style>
    .courses{
        display: flex;
        flex-wrap: wrap;
        justify-content: start;
        margin-left: 25px;
    }
    .course{
        margin-left: 15px;
        margin-bottom: 25px;
    }

</style>