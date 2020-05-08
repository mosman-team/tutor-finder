<template>
    <v-container>
        <v-row justify="center">

            <v-col cols="12" sm="12" md="12" lg="8">

                <v-card>
                    <v-tabs
                            v-model="tab"
                            background-color="indigo lighten-3"
                            centered
                            dark
                            icons-and-text
                    >
                        <v-tabs-slider></v-tabs-slider>

                        <v-tab href="#tab-1">
                            Course Details
                            <v-icon>details</v-icon>
                        </v-tab>

                        <v-tab href="#tab-2">
                            Messages
                            <v-icon>chat</v-icon>
                        </v-tab>

                    </v-tabs>

                    <v-tabs-items v-model="tab">
                        <v-tab-item
                                v-for="i in 2"
                                :key="i"
                                :value="'tab-' + i"
                        >
                        </v-tab-item>
                    </v-tabs-items>
                </v-card>


                <component :is="component" :id="id"></component>

            </v-col>
        </v-row>

    </v-container>

</template>

<script>
    import StudentCourseInfo from "./student/StudentCourseInfo.vue";
    import CourseInfo from "./teacher/CourseInfo.vue";
    import MessagesList from "./MessagesList.vue";

    export default {
        name: "DetailsMessageContainer",
        components: {
            StudentCourseInfo,
            CourseInfo,
            MessagesList
        },
        props: [
            'id'
        ],
        data(){
            return {
                component : '', // ''
                tab: null,
            }
        },
        computed: {
            currentUser () {
                return this.$store.state.auth.user
            },
            getCourseComponent(){
                if (this.currentUser.roles.includes('ROLE_TEACHER')){
                    return 'course-info'
                }else {
                    return 'student-course-info'
                }
            }
        },
        watch: {
            tab(newVal){
                if (newVal === 'tab-1'){
                    this.component = this.getCourseComponent;
                }else{
                    this.component = 'messages-list';
                }
            }
        },

    }
</script>

<style>


</style>