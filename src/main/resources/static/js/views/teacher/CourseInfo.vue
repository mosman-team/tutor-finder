<template>
<!--    <v-container v-if="!loading">-->
<!--        <v-row justify="center">-->
<!--            <v-col cols="12" sm="10" md="8" lg="6">-->
                <v-card v-if="!loading"
                        class="mx-auto"
                        max-width="800">
                    <v-img
                            height="250"
                            :src="'/img/'+getCourse.coursePic"
                    ></v-img>

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

                    <v-card-title>Content</v-card-title>

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

                    <v-card-title>Category</v-card-title>

                    <v-card-text>

                        <v-chip v-for="(item, i) in getCourse.keyWords"
                                class="ma-2"
                                text-color="white"
                                :color="colors[i%colors.length]">
                            {{item}}
                        </v-chip>

                    </v-card-text>
                    <v-card-actions>

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
        data() {
            return{
                twoLine : true,
                colors : ['primary', 'secondary', 'red', 'green'],
                loading : true
            }
        },
        created() {
            this.fetchCourseAction(this.id)
            this.fetchTopicsAction(this.id)
        },
        watch : {
            getCourse(newVal){
                this.loading = false
            }
        },
        computed : {
            ...mapGetters(['getCourse', 'getCourseTopics'])
        },
        methods : {
            ...mapActions(['fetchCourseAction', 'fetchTopicsAction']),
        }
    }
</script>

<style scoped>

</style>