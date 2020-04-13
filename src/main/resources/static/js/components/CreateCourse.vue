<template>
    <v-form ref="form" @submit.prevent="submitHandler">
        <v-card>
            <v-card-text>
                <v-text-field
                        ref="courseName"
                        v-model="course.courseName"
                        :rules="courseNameRules"
                        label="Course Name"
                        placeholder="Fantastic journey into the world of numbers"
                        required
                ></v-text-field>
                <v-textarea
                        ref="courseDesc"
                        v-model="course.courseDesc"
                        :rules="courseDescRules"
                        placeholder="This course full of ..."
                        label="Course Description"
                        required
                ></v-textarea>
                <v-file-input v-model="files" accept="image/*" label="Course Image" ref="courseIgm"></v-file-input>
            </v-card-text>
            <v-divider class="mt-12"></v-divider>
            <v-card-actions>
                <v-btn color="error" text @click="resetForm">Reset Form
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" text type="submit">Submit</v-btn>
            </v-card-actions>
        </v-card>
    </v-form>
</template>

<script>
    import Course from "../models/Course";
    import {mapActions} from 'vuex';
    export default {
        name : 'AddCourse',
        data() {
            return {
                course: new Course('Arithmetics', 'Test Description'),
                // rules
                courseNameRules: [],
                courseDescRules: [],
                // files
                files: [],
                //topic
            }
        },
        watch: {
            'course.courseName' (val){
                this.courseNameRules = []
            },
            'course.courseDesc' (val){
                this.courseDescRules = []
            }
        },
        methods: {
            ...mapActions(['addCourseAction']),
            resetForm () {
                this.$refs.form.reset()
            },
            submitHandler () {
                this.courseNameRules = [
                    v => !!v || 'Course name is required',
                ];
                this.courseDescRules = [
                    v => !!v || 'Course description is required',
                    // v => (v && v.length >= 10) || 'Name must be more than 10 characters',
                ];
                if (this.$refs.form.validate()){
                    //other codes
                    let formData = new FormData();
                    if (this.files.length !== 0){
                        formData.append("file", this.files);
                    }
                    formData.append("courseName",this.course.courseName)
                    formData.append("courseDesc",this.course.courseDesc)
                    this.addCourseAction(formData).then(
                        data => {
                            this.$store.dispatch('setSnackbarAction', {text : 'Course created successfully!'});
                        }, error =>{
                            this.$store.dispatch('setSnackbarAction', {color : 'error',text : 'Some fields are invalid!'});
                        }
                    );
                }
            }
        }
    }
</script>

<style scoped>

</style>