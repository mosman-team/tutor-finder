<template>
    <div class="msg-bottom">
        <form class="message-form" v-on:submit.prevent="sendMessage">
            <div class="input-group">
                <textarea type="text" class="form-control message-input"
                          placeholder="Type something" v-model="text" required>
                </textarea>

                <div>
                    <v-btn tile outlined color="success" class="mr-2 mt-2" type="submit">
                        <v-icon left>send</v-icon> Send
                    </v-btn>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "MessageForm",
        props: ['courseId', 'messageAttr'],
        components: {
        },
        data() {
            return {
                text: '',
                messageId: '',
                sendingMessage: false,
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.text = newVal.text
                this.messageId = newVal.id
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            sendMessage() {
                const data = {
                    messageId: this.messageId,
                    courseId: this.courseId,
                    text: this.text
                };

                if (this.messageId){
                    console.log('update message')

                    this.updateMessageAction(data);

                }else {

                    this.addMessageAction(data);
                }
                this.text = ''
                this.messageId = ''
            },
        }
    }
</script>

<style scoped>
    .msg-bottom {
        position: relative;
        height: 60px;
        background-color: #007bff;
        border-radius: 100px 100px 0 0;
    }

    .input-group {
        margin-right: 20px;
        border-top: 1px solid #DEE6EB;
        width: 100% !important;
        background-color: #fff;
        height: 100%;
        border-radius: 0 0 7px 7px
    }

    .input-group input {
        height: 100%;
        background: #fff;
    }

    .input-group ::placeholder {
        color: #C0C0C0 !important;
    }

    .form-control {
        border: none !important;
        border-radius: 20px !important;
    }

    .input-group-text {
        background: transparent !important;
        border: none !important;
    }

    .input-group .fa {
        color: #007bff;
        float: right;
    }

    .bottom-icons {
        float: left;
        margin-top: 17px;
        width: 30% !important;
        margin-left: 22px;
    }

    .bottom-icons .fa {
        color: #fff;
        padding: 5px;
    }

    .form-control:focus {
        border-color: none !important;
        box-shadow: none !important;
        border-radius: 20px;
    }

    .msg-bottom {
        border-radius: 0px 0px 10px 10px;
        position: absolute;
        z-index: 10;
        left: 0;
        bottom: 0;
        width: 100%;
    }

    .message-form {
        height: 100%;
    }


    nav .spinner {
        margin-left: 15px;
        width: 64px;
    }

    .sending-message-spinner {
        margin-right: 20px;
    }

    .message-input {
        padding: 6px 20px;
        overflow-wrap: break-spaces;
        resize: none;
        height: 58px;
        overflow:hidden
    }



</style>