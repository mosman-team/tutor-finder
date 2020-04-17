<template>
    <div>
        <v-card class="mb-2">
            <v-list
                    :three-line="threeLine"
                    :subheader="subheader"
                    :nav="nav">
                <v-subheader>TOPICS</v-subheader>
                <!--                <v-list-topic-group v-model="topic" color="primary">-->
                <v-list-item
                        v-for="(topic, i) in getCourseTopics"
                        :class="{'topicSelected' : i === selectedTopic}"
                        :key="i">
                    <v-list-item-content>
                        <v-list-item-title v-html=" '<i>' + (i+1) + ')' + '</i> ' + topic.title"></v-list-item-title>
                        <v-list-item-subtitle v-html="'<kbd>' + topic.hours +'h</kbd>'" ></v-list-item-subtitle>

                    </v-list-item-content>
                    <div class="d-flex flex-column">
                        <v-btn icon :class="{'hideUpArrowClass' : i === 0}">
                            <v-icon small>mdi-arrow-up</v-icon>
                        </v-btn>
                        <v-btn icon :class="{'hideDownArrowClass' : i === getCourseTopics.length - 1}">
                            <v-icon small>mdi-arrow-down</v-icon>
                        </v-btn>
                    </div>
                    <div class="d-flex">
                        <v-btn icon @click="editTopic(i)">
                            <v-icon small>mdi-pencil</v-icon>
                        </v-btn>
                        <v-btn icon @click="deleteTopic(i)">
                            <v-icon small>mdi-delete</v-icon>
                        </v-btn>
                    </div>
                </v-list-item>
                <!--                </v-list-topic-group>-->
            </v-list>
        </v-card>
        <v-form ref="form" @submit.prevent="submitHandler">
            <v-card>
                <v-subheader>Topic</v-subheader>
                <v-card-text>
                    <v-text-field
                            ref="title"
                            label="Title"
                            placeholder="Conditions"
                            v-model="topic.title"
                            required>
                    </v-text-field>
                    <v-text-field
                            ref="hours"
                            label="Hours"
                            placeholder="5"
                            v-model="topic.hours"
                            required>
                    </v-text-field>
                </v-card-text>
                <v-divider class="mt-12"></v-divider>
                <v-card-actions>
                    <v-btn text>Cancel</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text type="submit">Submit</v-btn>
                </v-card-actions>
            </v-card>
        </v-form>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex';
    import Topic from "../models/Topic";

    export default {
        name : 'AddCourse',
        data() {
            return {
                topic : new Topic(null,'', null),
                threeLine: true,
                twoLine : true,
                subheader: true,
                nav: false,
                selectedTopic : null
            }
        },
        computed : mapGetters(['getCourseTopics', 'getCurrentCourse']),
        created() {
            this.fetchTopicsAction()
        },
        methods: {
            ...mapActions(['addTopicAction', 'fetchTopicsAction', 'deleteTopicAction']),
            editTopic(i){
                if (this.selectedTopic !== i){
                    this.selectedTopic = i
                    this.topic.title = this.getCourseTopics[i].title
                    this.topic.hours = this.getCourseTopics[i].hours
                    this.topic.id = this.getCourseTopics[i].id
                }else {
                    this.selectedTopic = null
                    this.resetTopicInForm()
                }

            },
            deleteTopic(i){
                this.deleteTopicAction({courseId : this.getCurrentCourse.id, topicId : this.getCourseTopics[i].id})
            },
            resetForm () {
                this.$refs.form.reset()
            },
            resetTopicInForm(){
                this.topic = new Topic(null,'', null)
            },
            submitHandler () {
                if (this.$refs.form.validate()){
                    console.log(this.topic)
                    this.addTopicAction({ topic : this.topic, courseId : this.getCurrentCourse.id}).then(
                        data => {
                            this.$store.dispatch('setSnackbarAction', {text : 'Course topic created successfully!'});
                        }, error =>{
                            this.$store.dispatch('setSnackbarAction', {color : 'error',text : 'Some fields are invalid!'});
                        }
                    );
                    this.resetTopicInForm()
                }
            },
            swap(i, j){

            }
        }
    }
</script>

<style scoped>
    .topicSelected{
        background: powderblue;
    }
    .hideUpArrowClass{
        display: none;
    }
    .hideDownArrowClass{
        display: none;
    }

</style>
