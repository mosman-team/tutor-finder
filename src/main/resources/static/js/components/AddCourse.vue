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
                                    :flat="flat"
                                    :rules="courseNameRules"
                                    required
                            ></v-text-field>
                            <v-textarea
                                    v-model="course.courseDesc"
                                    outlined
                                    placeholder="Course description"
                                    :flat="flat"
                                    required
                            ></v-textarea>
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
    import authHeader from '../services/auth-header';
    import axios from "axios";
    export default {
        name : 'AddCourse',
        data() {
            return {
                // form position
                alignment: 'center',
                justify: 'center',
                course: new Course('', '', ''),
                items: [
                    'Math',
                    'Physics',
                    'CSS',
                    'Electronics',
                ],
                flat: true,
                valid: false,
                // rules
                courseNameRules: [],

                // files
                files: []
            }
        },
        watch: {
            'course.courseName' (val){
                this.courseNameRules = []
            }
        },
        methods: {
            submitHandler () {
                this.courseNameRules = [
                    v => !!v || 'Course name is required',
                ];
                if (this.$refs.form.validate()){
                    //other codes
                    let formData = new FormData();
                    formData.append("file", this.files);
                    formData.append("courseName",this.course.courseName)
                    formData.append("courseDesc",this.course.courseDesc)
                    formData.append("coursePic",this.course.coursePic)

                    axios.post("http://localhost:8080/teachers/1/courses", formData,
                        {
                            headers: {
                                "Authorization" : authHeader().Authorization,
                                "Content-Type": "multipart/form-data"
                            }
                        }
                    ).then(console.log).catch(console.log);
                }
            }
        }
    }
</script>

<style scoped>

</style>