<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="8" md="6" lg="4">

                <v-slider
                        v-if="renderComponent"
                        :key="courseAddStage"
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

                <component :setVisited="setVisited" :is="component"></component>

            </v-col>
        </v-row>
    </v-container>

</template>

<script>
    import CreateCourse from "../components/CreateCourse.vue";
    import SetCourseTopics from "../components/SetCourseTopics.vue";
    import SetCourseInfo from "../components/SetCourseInfo.vue";
    import {mapGetters, mapMutations} from 'vuex';
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
                    'Create',
                    'Set Course Topics',
                    'Info',
                ],
                icons: [
                    'create',
                    'note_add',
                    'info'
                ],
                courseAddStage: 0,
                renderComponent: true,
                visited: [true, false, false]
            }
        },
        computed : mapGetters(["getCurrentCourse", "getInfo"]),
        watch : {

            courseAddStage(newVal, oldVal){

                if (!this.visited[newVal]){
                    this.setCourseAddStage(oldVal)
                    this.forceRerender()
                }

                this.component = this.addCourseComponents[this.courseAddStage]

            }
        },
        methods: {
            ...mapMutations(['emptyCourseMutation', 'emptyCourseInfoMutation']),
            season (val) {
                return this.icons[val]
            },
            setCourseAddStage(v){
                this.courseAddStage = v
            },
            forceRerender() {
                // Remove my-component from the DOM
                this.renderComponent = false;

                this.$nextTick(() => {
                    // Add the component back in
                    this.renderComponent = true;
                });
            },
            setVisited(index, b){
                this.visited[index] = b
            }
        },
        created() {
            if (this.getCurrentCourse.id){
                this.setVisited(1, true)
            }
            if (this.getInfo.id){
                this.setVisited(2, true)
            }
        },
        beforeDestroy() {
            this.emptyCourseInfoMutation();
            this.emptyCourseMutation();
            sessionStorage.clear();
        }
    }
</script>

<style scoped>

</style>