<template>
    <div id="app">
        <v-app id="inspire">
            <v-form ref="form" @submit.prevent="submitHandler">
                <v-container fluid>
                    <v-row :align="alignment"
                           :justify="justify">
                        <v-col cols="12"
                                md="8">
                            <v-text-field
                                    v-model="course.courseName"
                                    hint="Fantastic journey into the world of numbers"
                                    placeholder="Course name"
                                    outlined
                                    flat
                                    :rules="courseNameRules"
                                    required
                            ></v-text-field>
                            <v-textarea
                                    v-model="course.courseDesc"
                                    outlined
                                    placeholder="Course description"
                                    flat
                                    required
                            ></v-textarea>
                            <v-row justify="center">
                                <v-col cols="12" sm="10" md="8" lg="12">
                                    <v-card outlined ref="form">
                                        <div class="content"></div>
                                        <v-divider class="mt-12"></v-divider>
                                        <v-card-text>
                                            <v-text-field
                                                    ref="topicTitle"
                                                    label="Topic"
                                                    placeholder="Conditions"
                                                    v-model="topicTitle"
                                                    required>
                                            </v-text-field>
                                            <v-text-field
                                                    ref="hours"
                                                    label="Hours"
                                                    placeholder="5h"
                                                    v-model="topicHours"
                                                    required>
                                            </v-text-field>
                                        </v-card-text>
                                        <v-card-actions>
                                            <v-btn color="primary" text>Submit</v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-col>
                            </v-row>
                            <v-file-input v-model="files" label="Course image" outlined dense>
                            </v-file-input>
                            <v-row>
                                <v-btn type="submit" color="success">Submit</v-btn>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-container>
            </v-form>
        </v-app>
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
                // form position
                alignment: 'center',
                justify: 'center',
                course: new Course('Arithmetics', 'TestDesc', ''),
                flat: true,
                valid: false,
                // rules
                courseNameRules: [],

                // files
                files: [],
                //topic
                topics : [],
                topicTitle : 'MyTitle',
                topicHours : 3
            }
        },
        watch: {
            'course.courseName' (val){
                this.courseNameRules = []
            }
        },
        methods: {
            ...mapActions(['addCourseAction']),
            submitHandler () {
                this.courseNameRules = [
                    v => !!v || 'Course name is required',
                ];
                if (this.$refs.form.validate()){
                    //other codes
                    this.topics[0] = new Topic(this.topicTitle, this.topicHours);

                    let formData = new FormData();
                    formData.append("file", this.files);
                    formData.append("courseName",this.course.courseName)
                    formData.append("courseDesc",this.course.courseDesc)
                    formData.append("coursePic",this.course.coursePic)

                    formData.append('topics', JSON.stringify(this.topics));


                    this.addCourseAction(formData);
                    /*axios.post("http://localhost:8080/teachers/1/courses", formData,
                        {
                            headers: {
                                "Authorization" : authHeader().Authorization,
                                "Content-Type": "multipart/form-data"
                            }
                        }
                    ).then(console.log).catch(console.log);*/
                }
            }
        }
    }
</script>

<style scoped>

</style>