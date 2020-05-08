<template>
    <!--    <v-container v-if="!loading">-->
    <!--        <v-row justify="center">-->
    <!--<v-col cols="12" sm="10" md="8" lg="6">-->

    <v-card
            class="mx-auto"
            max-width="800" v-if="!loading">

        <v-list-item @click="profilePage">
            <v-list-item-avatar color="grey">
                <v-img :src="this.getCourse.teacher.img"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title class="headline">{{getCourse.teacher.username}}</v-list-item-title>
            </v-list-item-content>
        </v-list-item>

        <div class="img-container">
            <v-img
                    :src="'/img/'+getCourse.coursePic"
                    class="course-img"
            ></v-img>
        </div>

        <v-card-title>{{getCourse.courseName}}</v-card-title>
        <v-card-text>

            <span class="body-1 font-weight-bold">Price:  </span> {{getCourse.price}}

            <div class="my-4 subtitle-1">
                <span class="font-weight-bold">City:</span> {{getCourse.city}}
            </div>
            <div class="my-4 subtitle-1">
                <span class="font-weight-bold">Address :</span> {{getCourse.address}}
            </div>
            <div class="my-4 subtitle-1">
                <span class="font-weight-bold">Language :</span> {{getCourse.language}}
            </div>
            <div class="title">Course description</div>
            <div>{{getCourse.courseDesc}}</div>
        </v-card-text>
        <v-divider class="mx-4"></v-divider>

        <v-card-title>Контент Курса</v-card-title>

        <v-card-text>
            <v-list-item
                    v-for="(topic, i) in getCourseTopics"
                    :key="i">
                <v-list-item-content>
                    <v-list-item-title v-html=" '<i>' + (i+1) + ')' + '</i> ' + topic.title"></v-list-item-title>
                    <v-list-item-subtitle v-html="'<kbd>' + topic.hours +'h</kbd>'" ></v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>

        <v-card-actions>
            <v-btn v-if="!hasEnrolled"
                   color="deep-purple lighten-2"
                   class="mx-auto"
                   text
                   @click="enroll">
                Enroll
            </v-btn>
            <v-btn v-if="hasEnrolled"
                   color="red lighten-2"
                   class="mx-auto"
                   text
                   @click="enroll">
                Unenroll
            </v-btn>
        </v-card-actions>
    </v-card>
    <!--            </v-col>-->
    <!--        </v-row>-->
    <!--    </v-container>-->
</template>

<script>

    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "CourseInfo",
        props : ['id'],
        data () {
            return{
                twoLine : true,
                colors : ['primary', 'secondary', 'red', 'green'],
                loading : true,
            }
        },
        created() {
            this.fetchStudentCourseAction(this.id)
            this.fetchTopicsAction(this.id)
            this.fetchEnrolledCoursesIds()
        },
        watch : {
            getCourse(newVal){
                this.loading = false
            }
        },
        computed : {
            ...mapGetters(['getCourse', 'getCourseTopics', 'getEnrolledCoursesIds']),
            hasEnrolled : function(){
                const index = this.getEnrolledCoursesIds.findIndex(item => item.id === this.getCourse.id)
                return index > -1
            }
        },
        methods : {
            ...mapActions(['fetchStudentCourseAction', 'fetchTopicsAction',
                'enrollCourseAction', 'fetchEnrolledCoursesIds']),
            enroll(){
                this.enrollCourseAction(this.getCourse.id) // unenrolls student if already enrolled
            },
            profilePage(){
                // send to teacher profile
                this.$router.push({ name: 'UserProfile', params: {id: this.getCourse.teacher.id}})
            }
        }
    }
</script>

<style scoped>
    .course-img{
        max-width: 100%;
        max-height: 100%;
        object-fit: contain;
    }
    .img-container{
        height: 280px;
    }
</style>