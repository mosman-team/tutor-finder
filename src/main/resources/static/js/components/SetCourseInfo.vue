<template>
    <div>
        <h1>
            Set course info
        </h1>

        <v-btn small color="primary" @click="this.courseCreationProcedureFinished" >Finish</v-btn>

    </div>
</template>

<script>
    import {mapMutations} from 'vuex';

    export default {
        name: "SetCourseInfo",
        props: [
            'changeCourseStage'
        ],
        methods: {
            ...mapMutations(['courseCreationProcedureFinished']),

        }
    }
</script>

<style scoped>

</style>