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
                    v-for="(topic, i) in topics"
                    :class="{'topicSelected' : topic.edit}"
                    :key="i"
                    @click="clickedtopic(i)"
                    v-on:mouseover="mouseover(i)"
                    v-on:mouseleave="mouseleave(i)">
                    <v-list-item-content>
                        <v-list-item-title v-html=" '<i>' + (i+1) + ')' + '</i> ' + topic.title"></v-list-item-title>
                        <v-list-item-subtitle v-if="twoLine || threeLine" v-html="topic.subtitle + ' <kbd>' + topic.hours +'h</kbd>'" ></v-list-item-subtitle>
                    </v-list-item-content>
                    <div class="d-flex flex-column" v-if="!topic.edit">
                        <v-btn icon v-if="topic.upBtn" @click.stop="swap(i, i-1)">
                            <v-icon small>mdi-arrow-up</v-icon>
                        </v-btn>
                        <v-btn icon v-if="topic.downBtn" @click.stop="swap(i, i+1)">
                            <v-icon small>mdi-arrow-down</v-icon>
                        </v-btn>
                    </div>
                    <div class="d-flex" v-if="topic.edit">
                        <v-btn icon>
                            <v-icon small>mdi-pencil</v-icon>
                        </v-btn>
                        <v-btn icon @click.stop="deleteTopic(i)">
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
                    v-model="title"
                    required>
                </v-text-field>
                <v-text-field
                    ref="subtitle"
                    label="Title"
                    placeholder="Conditions"
                    v-model="subtitle"
                    required>
                </v-text-field>
                <v-text-field
                    ref="hours"
                    label="Hours"
                    placeholder="5h"
                    v-model="hours"
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
    import Course from "../models/Course";
    import {mapActions} from 'vuex';
    import Topic from "../models/Topic";

    export default {
        name : 'AddCourse',
        data() {
            return {
                topic: {
                    title: '',
                    subtitle: '',
                    hours : null,
                    upBtn : false,
                    downBtn : false,
                    edit : false,
                },
                topics: [],
                threeLine: true,
                twoLine : true,
                subheader: true,
                nav: false,

                //topic
                title : 'Title',
                subtitle : 'Subtitle',
                hours : 3,
            }
        },
        methods: {
            resetForm () {
                this.$refs.form.reset()
            },
            submitHandler () {
                if (this.$refs.form.validate()){
                    //other codes
                    this.topics[0] = new Topic(this.title, this.subtitle, this.hours);
                }
            },
            clickedtopic(i){
                let len = this.topics.length;
                let j = 0; // activated topic index
                while (j < len && this.topics[j].edit !== true){
                    j++;
                }
                if (j < len){
                    if (i !== j){
                        this.topics[j].edit = false;
                        this.topics[i].edit = true;
                        this.topic = i;
                    }else if (i === j){
                        this.topics[i].edit = false;
                        this.topic = -1;
                    }
                }else {
                    this.topics[i].edit = true;
                    this.topic = i;
                }
            },
            mouseover(i){
                if (i > 0){
                    this.topics[i].upBtn = true
                }
                if (i < this.topics.length - 1){
                    this.topics[i].downBtn = true
                }
            },
            mouseleave(i){
                this.topics[i].upBtn = false
                this.topics[i].downBtn = false
            },
            swap(i, j){
                let iObj = this.topics[i];
                let jObj = this.topics[j];

                let title = iObj.title;
                let subtitle = iObj.subtitle;
                let hours = iObj.hours;

                iObj.title = jObj.title;
                iObj.subtitle = jObj.subtitle;
                iObj.hours = jObj.hours;

                jObj.title = title;
                jObj.subtitle = subtitle;
                jObj.hours = hours;
            },
            deleteTopic(i){
                this.topics.splice(i,1)
            }
        }
    }
</script>

<style scoped>
    .topicSelected{
        background: powderblue;
    }
</style>