<template>
    <div>
    <!--<v-parallax
            dark
            src="http://localhost:8080/static/img/educationImg.jpg">
        <v-row
                align="center"
                justify="center">
            <v-col class="text-center" cols="12">
                <h1 class="display-1 font-weight-thin mb-4">Tutor Finder</h1>
                <h4 class="subheading">Find appropriate teacher and study with pleasure</h4>
            </v-col>
        </v-row>
    </v-parallax>-->
        <v-row justify="center" align="center">
            <v-col cols="9">
                <v-img class="mx-auto" src="http://localhost:8080/static/img/homeImg.jpg">
                    <div class="home-container">
                        <div class="home-text ml-5">
                            <h1 class="display-3 font-weight-thin mb-4">Tutor Finder</h1>
                            <h4 class="subheading">Find appropriate teacher and study with pleasure</h4>
                            <div class="my-2 d-flex justify-center" v-if="!currentUser">
                                <v-btn @click="redirectToRegister" large color="primary">Join</v-btn>
                            </div>
                        </div>
                    </div>
                </v-img>
            </v-col>
        </v-row>
        <v-row
                align="center"
                justify="center">
            <v-col v-for="(data,i) in cardData" class="text-center" cols="3">
                <v-card
                    class="mx-auto"
                    max-width="344"
                    outlined>
                    <v-list-item three-line>
                        <v-list-item-content :class="'listItem'+i">
                            <div class="display-1 mb-4 number">{{data.count}}</div>
                            <v-list-item-title class="headline mb-1">{{data.type}}</v-list-item-title>
                            <v-list-item-subtitle>{{data.text}}</v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-avatar
                                tile
                                size="80">
                            <v-img :src="data.img"></v-img>
                        </v-list-item-avatar>
                    </v-list-item>
                </v-card>
            </v-col>
        </v-row>
    </div>
</template>

<script>

    export default {
        name: 'Home',
        data() {
            return {
                cardData : [
                    {
                        count : 99,
                        type : 'teachers',
                        text : 'Greyhound divisely hello coldly fonwderfully',
                        img : 'http://localhost:8080/static/img/teacher.png'
                    },
                    {
                        count : 150,
                        type : 'courses',
                        text : 'Greyhound divisely hello coldly fonwderfully',
                        img : 'http://localhost:8080/static/img/defaultCourseImg.png'
                    },
                    {
                        count : 1000,
                        type : 'students',
                        text : 'Greyhound divisely hello coldly fonwderfully',
                        img : 'http://localhost:8080/static/img/student.png'
                    },
                ],
            };
        },
        methods : {
            redirectToRegister(){
                this.$router.push('/register');
            }
        },
        computed : {
            currentUser() {
                return this.$store.state.auth.user;
            }
        }
    };
</script>

<style>
    .home-container{
        height: 100%;
        display: flex;
        align-items: center;
    }
    .home-text{
        color: white;
    }

    .listItem0{
        color: blueviolet;
    }
    .listItem1{
        color: greenyellow;
    }
    .listItem2{
        color: orangered;
    }
    .number {
        font-style: italic;
    }

</style>