<template>
    <div class="media-container">
        <v-flex v-if="type === 'href'" xs8 sm12>
            <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75"></v-img>
            <v-card-title>
                <div>
                    <h3>
                        <a :href="message.link">
                            <span class="message-link">
                                {{message.linkTitle || message.link}}
                            </span>
                        </a>
                    </h3>
                    <div v-if="message.linkDescription">{{message.linkDescription}}</div>
                </div>
            </v-card-title>
        </v-flex>
        <v-flex v-if="type === 'image'" xs8 sm12>
            <a :href="message.link">
                <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75"></v-img>
                <span class="image-link">
                    {{message.link}}
                </span>
            </a>
        </v-flex>
        <v-flex v-if="type === 'youtube'" xs8 sm12>
            <you-tube :src="message.link"></you-tube>
        </v-flex>
    </div>
</template>

<script>
    import YouTube from './YouTube.vue'
    export default {
        name: 'Media',
        components: { YouTube },
        props: ['message'],
        data() {
            return {
                type: 'href'
            }
        },
        beforeMount() {
            if (this.message.link.indexOf('youtu') > -1 && this.message.link.indexOf('watch') > -1) {
                this.type = 'youtube'
            } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
                this.type = 'image'
            } else {
                this.type = 'href'
            }
        }
    }
</script>

<style scoped>
    .media-container{
        display: flex;
        justify-content: center;
    }
    .message-link{
        font-size: 20px;
    }
    .image-link{
        font-size: 15px;
    }
</style>