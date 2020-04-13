<template>
    <v-form ref="form" @submit.prevent="submitHandler">
    <v-card>
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
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
            <v-btn text>Cancel</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="primary" text type="submit">Submit</v-btn>
        </v-card-actions>
    </v-card>
    </v-form>
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
                course: new Course('Arithmetics', 'TestDesc', ''),
                // rules

                //topic
                topics : [],
                topicTitle : 'Title',
                topicHours : 3,
            }
        },
        watch: {

        },
        methods: {
            resetForm () {
                this.$refs.form.reset()
            },
            submitHandler () {
                if (this.$refs.form.validate()){
                    //other codes
                    this.topics[0] = new Topic(this.topicTitle, this.topicHours);

                }
            }
        }
    }
</script>

<style scoped>

</style>