<template>
    <v-form ref="form" @submit.prevent="submitHandler" v-model="valid" :lazy-validation="lazy">
        <v-card>
            <v-card-text>
                <v-text-field
                        ref="courseName"
                        v-model="getCurrentCourse.courseName"
                        :rules="courseNameRules"
                        label="Course Name"
                        placeholder="Fantastic journey into the world of numbers"
                        required
                ></v-text-field>
                <v-textarea
                        ref="courseDesc"
                        v-model="getCurrentCourse.courseDesc"
                        :rules="courseDescRules"
                        placeholder="This course full of ..."
                        label="Course Description"
                        required
                ></v-textarea>
                <v-file-input v-model="files" accept="image/*" label="Course Image" ref="courseIgm" @change="changeCourseImg">

                </v-file-input>
                <span class="mr-4">Current Image:</span>
                <v-chip
                        v-if="courseImg.display"
                        class="ma-2"
                        close
                        :color="courseImg.color"
                        text-color="white"
                        @click:close="removeCourseImg"
                >
                    {{courseImg.name}}
                </v-chip>
            </v-card-text>
            <v-divider class="mt-12"></v-divider>
            <v-card-actions>
                <v-btn color="error" text @click="resetForm">Reset Form
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" text type="submit" :disabled="!valid">Submit</v-btn>
            </v-card-actions>
        </v-card>
    </v-form>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex';

    export default {
        name : 'AddCourse',
        data() {
            return {
                valid : true,
                lazy : false,
                // rules
                courseNameRules : [
                    v => !!v || 'Course name is required',
                ],
                courseDescRules : [
                    v => !!v || 'Course description is required',
                    // v => (v && v.length >= 10) || 'Name must be more than 10 characters',
                ],
                // files
                files: [],

            }
        },
        computed: {
            ...mapGetters(['getCurrentCourse', 'getCourseImgName']),
            courseImg : function () {
                return {
                    display : this.getCourseImgName != null,
                    color : "orange",
                    name : this.getCourseImgName,
                }
            }
        },
        methods: {
            ...mapActions(['addCourseAction', 'updateCourseAction']),
            ...mapMutations(['setCourseImgNameMutation']),
            changeCourseImg(){
                if (this.files){
                    this.courseImg.display = true
                    this.courseImg.name = this.files.name
                    this.courseImg.color = "green"
                }else {
                    this.courseImg.display = false
                    this.courseImg.name = ''
                }
            },
            resetForm () {
                this.$refs.form.reset()
            },
            removeCourseImg(){
                this.courseImg.display = false
                this.courseImg.name = ''
                this.files = []
            },
            submitHandler () {
                if (this.$refs.form.validate()){
                    //other codes
                    let formData = new FormData();
                    if (this.files.length !== 0){
                        formData.append("file", this.files);
                    }
                    formData.append("courseName", this.getCurrentCourse.courseName)
                    formData.append("courseDesc", this.getCurrentCourse.courseDesc)
                    formData.append("coursePic", this.courseImg.name)

                    if (this.getCurrentCourse.id){
                        this.updateCourseAction({formData : formData, courseId : this.getCurrentCourse.id})
                    }else {
                        this.addCourseAction(formData);
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>