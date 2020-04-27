<template>
    <v-card width="344">
        <v-img
            :src="'/img/'+course.coursePic"
            height="194"
        ></v-img>
        <v-card-title>
            {{course.courseName}}
        </v-card-title>
        <v-card-text>
            {{course.courseDesc}}
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple accent-4" @click="viewCourse">
                view
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn icon @click="editCourse(index)">
                <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn icon @click="deleteCourseAction({courseId : course.id})">
                <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import {router} from "../router";

    export default {
        name: "Course",
        props : [
            'course', 'deleteCourseAction', 'editCourse', 'index'
        ],
        methods : {
            viewCourse(){
                this.$router.push({ name: 'DetailsMessageContainer', params: {id: this.course.id}})
            }
        }
    }
</script>

<style scoped>

</style>