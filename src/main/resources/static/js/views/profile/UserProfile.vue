<template>
    <v-container>
        <v-row justify="center">
            <v-card width="500" height="400">
                <v-card-text v-if="getSelectedUser">
                    <v-flex class="mb-4 d-flex flex-column align-center">
                        <v-avatar size="96" class="mr-4">
                            <img :src="getSelectedUser.img" alt="Avatar">
                        </v-avatar>
                    </v-flex>

                    <v-text-field
                            readonly
                            v-model="getSelectedUser.username"
                            label="Username"></v-text-field>
                    <v-text-field
                            readonly
                            v-model="getSelectedUser.email"
                            label="Email Address"></v-text-field>
                </v-card-text>

                <v-card-actions>
                    <v-btn v-if="getSelectedUser && userIsTeacher" @click="seeTeacherCourses" class="primary">My courses</v-btn>
                </v-card-actions>
            </v-card>
        </v-row>
    </v-container>

</template>

<script>
    export default {
        name: "UserProfile",
        props : ['id'],
        data () {
            return {
                loading : false
            }
        },
        created() {
            if (this.id){
                this.$store.dispatch('auth/fetchSelectedUser', this.id)
            }
        },
        computed : {
            getSelectedUser () {
                return this.$store.getters["auth/getSelectedUser"]
            },
            userIsTeacher(){
                return this.getSelectedUser.roles[0].name === 'ROLE_TEACHER'
            }
        },
        methods : {
            seeTeacherCourses(){
                this.$router.push({ name: 'SelectedTCourses',
                    params: {id : this.getSelectedUser.id, name : this.getSelectedUser.username}})
            }
        }
    }
</script>

<style scoped>

</style>