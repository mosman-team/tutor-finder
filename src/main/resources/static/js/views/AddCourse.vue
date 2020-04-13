<template>
    <v-row justify="center">
        <v-col cols="12" sm="10" md="8" lg="6">
            <component :is="component"></component>

        </v-col>
    </v-row>
</template>

<script>
    import CreateCourse from "../components/CreateCourse.vue";
    import SetCourseTopics from "../components/SetCourseTopics.vue";
    import {mapGetters} from 'vuex';
    export default {
        name : 'AddCourse',
        components : {
            CreateCourse,
            SetCourseTopics
        },
        data() {
            return {
                component : 'create-course'
            }
        },
        computed : mapGetters(["currentCourse"]),
        watch : {
            currentCourse(){
                this.component = 'set-course-topics'
            }
        }
    }
</script>

<style scoped>

</style>