<template>
    <iframe
            :src="targetSrc"
            frameborder="0"
            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen></iframe>
</template>

<script>
    export default {
        name: 'YouTube',
        props: ['src'],
        computed: {
            targetSrc() {
                let u = this.src.split('watch?v=');
                let path = (u[1]).split('&');
                return 'https://www.youtube.com/embed/' + path[0];
            }
        }
    }
</script>

<style scoped>
    iframe{
        width: 100%;
        height: 280px;
    }
    /*@media (max-width: 600px) {
        iframe{
            width: 60%;
            height: 140px;
        }
    }

    @media (min-width: 600px) {

    }*/


</style>