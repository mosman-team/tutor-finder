<template>
    <!--<v-parallax
            dark
            src="http://localhost:8080/static/img/homeImg.jpg">
        <v-row
                align="center"
                justify="center">
            <v-col class="text-center" cols="12">
                <h1 class="display-1 font-weight-thin mb-4">Vuetify.js</h1>
                <h4 class="subheading">Build your application today!</h4>
            </v-col>
        </v-row>
    </v-parallax>
    -->


    <v-img class="mx-auto" width="1000" src="http://localhost:8080/static/img/homeImg.jpg">
        <v-row
                align="center"
                justify="center">
            <v-col class="text-center" cols="12">
                <h1 class="display-1 font-weight-thin mb-4">Vuetify.js</h1>
                <h4 class="subheading">Build your application today!</h4>
            </v-col>
        </v-row>
    </v-img>

</template>

<script>

    export default {
        name: 'Home',
        data() {
            return {
                content: 'hi'
            };
        },
        mounted() {

        }
    };
</script>