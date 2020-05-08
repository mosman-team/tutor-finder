<template>

    <v-app>
        <Navbar />
        <v-content class="grey lighten-4">
            <div class="mx-4 my-10">
                <router-view></router-view>
            </div>
        </v-content>
        <!--    app snackbar (notification)-->
        <v-snackbar
                v-for="(snackbar, index) in snackbars.filter(s => s.showing)"
                :key="snackbar.text + Math.random()"
                v-model="snackbar.showing"
                :timeout="snackbar.timeout"
                :color="snackbar.color"
                :style="`bottom: ${(index * 60) + 8}px`"
        >
            {{snackbar.text}}
            <v-btn text @click="snackbar.showing = false">Close</v-btn>
        </v-snackbar>

    </v-app>


</template>
<script>
    import Navbar from "../components/Navbar.vue";
    import {mapState, mapMutations} from 'vuex';
    import {addHandler} from "../utils/ws";

    export default {
        components: {
            Navbar
        },
        computed: {
            ...mapState(['snackbars']),
            currentUser() {
                return this.$store.state.auth.user;
            },
            showStudentBoard() {
                if (this.currentUser && this.currentUser.roles) {
                    return this.currentUser.roles.includes('ROLE_STUDENT');
                }

                return false;
            },
            showTeacherBoard() {
                if (this.currentUser && this.currentUser.roles) {
                    return this.currentUser.roles.includes('ROLE_TEACHER');
                }

                return false;
            }
        },
        methods: {
            ...mapMutations(['addMessageMutation','updateMessageMutation','removeMessageMutation']),
        },
        created() {
            addHandler(data => {
                console.log(data);
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body);
                            break;
                        case 'UPDATE':
                            this.updateMessageMutation(data.body);
                            break;
                        case 'REMOVE':
                            this.removeMessageMutation(data.body);
                            break;
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        }
    };
</script>