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
        </v-card-actions>
    </v-card>

</template>

<script>
    export default {
        name: "StudentCourse",
        props : [
            'course'
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