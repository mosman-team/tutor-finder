<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="8" md="6" lg="4">

                <p v-if="!currentCourse.id" class="headline text-center green--text text--lighten-2">Create Course</p>

                <v-slider v-if="currentCourse.id"
                          class="font-weight-light"
                          v-model="courseAddStage"
                          :tick-labels="seasons"

                          :color="'light-green lighten-2'"
                          :track-color="'indigo lighten-4'"
                          thumb-label="always"

                          :max="2"
                          step="1"
                          ticks="always"
                          tick-size="3"
                >
                    <template v-slot:thumb-label="props">
                        <v-icon dark>
                            {{ season(props.value) }}
                        </v-icon>
                    </template>
                </v-slider>


            </v-col>

        </v-row>

        <v-row justify="center">

            <v-col cols="12" sm="10" md="8" lg="6">

                <component :is="component"></component>

            </v-col>
        </v-row>
    </v-container>

</template>

<script>
    import CreateCourse from "../components/CreateCourse.vue";
    import SetCourseTopics from "../components/SetCourseTopics.vue";
    import SetCourseInfo from "../components/SetCourseInfo.vue";
    import {mapGetters} from 'vuex';
    export default {
        name : 'AddCourse',
        components : {
            CreateCourse,
            SetCourseTopics,
            SetCourseInfo
        },
        data() {
            return {
                component : 'create-course',
                addCourseComponents: [
                    'create-course',
                    'set-course-topics',
                    'set-course-info'
                ],
                seasons: [
                    'Edit',
                    'Set Course Topics',
                    'Info',
                ],
                icons: [
                    'create',
                    'note_add',
                    'info'
                ],
                courseAddStage: 0
            }
        },
        computed : mapGetters(["currentCourse"]),
        watch : {
            currentCourse(newVal){
                if (newVal.id !== null){
                    this.courseAddStage = 1
                }
            },
            courseAddStage(){
                this.component = this.addCourseComponents[this.courseAddStage]
            }
        },
        methods: {
            season (val) {
                return this.icons[val]
            }
        },
    }
</script>

<style scoped>

</style>