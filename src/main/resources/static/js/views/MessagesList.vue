<template>

    <div class="booker">
        <div class="chat">
            <div class="container">
                <div class="msg-header">
                    <div class="active">
                        <h5> <strong>#{{getCourse.courseName}}</strong> Chat</h5>
                    </div>
                </div>

                <div class="chat-page">
                    <div class="msg-inbox">
                        <div class="chats" id="chats">
                            <div class="msg-page" id="msg-page">

                                <div
                                        v-if="loadingMessages"
                                        class="loading-messages-container"
                                >
                                    <spinner :size="100"/>
                                    <span class="loading-text">
                                        Loading Messages
                                    </span>
                                </div>

                                <div class="text-center img-fluid empty-chat" v-else-if="!sortedMessages.length" >
                                    <div class="empty-chat-holder">
                                        <img src="/static/img/empty-state.svg" class="img-res" alt="empty chat image">
                                    </div>

                                    <div>
                                        <h2> No new message? </h2>
                                        <h6 class="empty-chat-sub-title">
                                            Send your first message below.
                                        </h6>
                                    </div>
                                </div>

                                <div v-else class="messages-container">

                                    <message-row v-for="message in sortedMessages"
                                                 v-bind:key="message.id"
                                                 :message="message"
                                                 :editMessage="editMessage"
                                                 :currentUserId="currentUser.id"
                                    />

                                </div>

                            </div>
                        </div>
                    </div>

                    <MessageForm :courseId="id" :messageAttr="message"/>

                </div>
            </div>
        </div>
    </div>

</template>

<script>
    import Spinner from "../components/Spinner.vue";
    import MessageRow from "../components/messages/MessageRow.vue";

    import { mapActions, mapGetters } from 'vuex';
    import MessageForm from "../components/messages/MessageForm.vue";

    export default {
        name: "MessagesList",
        props: [
            'id'
        ],
        components: {
            Spinner,
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: "",
                sendingMessage: false,
                loggingOut: false,
                loadingMessages: false
            }
        },
        computed: {
            ...mapGetters(['sortedMessages', 'getCourse']),
            currentUser(){
                return this.$store.state.auth.user;
            }
        },
        methods: {
            ...mapActions(['fetchCourseMessagesAction']),
            editMessage(message) {
                this.message = message
            },
        },
        created() {
            this.fetchCourseMessagesAction(this.id);
        },

    }
</script>

<style scoped>
    @import 'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css';
    @import 'https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css';
    @import url('https://fonts.googleapis.com/css?family=Abril+Fatface&display=swap');
    @import url('https://fonts.googleapis.com/css?family=Roboto&display=swap');


    /*my css*/

    .messages-container{
        overflow: auto;
        display: flex;
        flex-direction: column-reverse;
        height: 98%;
    }


    * {
        box-sizing: border-box;
    }

    body {
        color: #333;
        font-size: 13px;
        margin: 0;
        width: 100%;
        height: 100vh;
        -webkit-font-smoothing: antialiased;
    }

    h3 {
        font-family: 'Abril Fatface';
        margin-bottom: 20px;
    }


    label {
        color: #c9d4d8;
        font-weight: bold;
    }

    .form-wrapper label {
        margin: 0;
        color: #BFCDD8;
    }

    .form-wrapper #username {
        border-bottom: 2px solid #E6ECEE !important;
        border-radius: 0 !important;
        padding-top: 0.375rem;
        padding-right: 0.75rem;
        padding-bottom: 0.375rem;
        padding-left: 0;
    }

    .form-wrapper i {
        position: absolute;
        bottom: 9px;
        right: 0;
    }

    nav {
        flex: 1;
        height: 50px;
        line-height: 50px;
        background: #fff;
        color: black;
        font-size: 14px;
        padding: 0 50px;
        display: flex;
        justify-content: space-between;
    }

    .chat {
        /*flex: 1;
        padding-top: 40px;
        background-color: #204CD2;
        background-image: url(/static/img/chat-background.svg);
        background-repeat: no-repeat;
        background-position: center top;
        background-size: cover;
        width: 100%;*/
    }

    .empty-chat-holder {
        width: 100%;
        height: 250px;
        margin-top: 70px;
    }

    .empty-chat {
        position: relative;
        margin: auto;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        width: 100%;
    }

    .empty-chat h2 {
        color: #1546dc;
        font-family: 'Abril Fatface';
        padding: 0;
        font-size: 2rem;
        margin: 25px auto 15px;
    }

    .empty-chat-holder img {
        width: 100%;
        height: 100%;
    }


    .container {
        margin: auto;
        max-width: 800px;
        /*width: calc(100% - 20px);*/
        height: 588px;
        font-family: sans-serif;
        letter-spacing: 0.5px;
        background: #f8f9fb;
        padding: 0 !important;
        box-shadow: 0 0 50px rgba(0, 0, 0, 0.2);
        border-radius: 7px;
        box-sizing: border-box;
        border: 1px solid #BFCDD8;
    }

    .msg-header {
        width: 100%;
        height: 70px;
        border-bottom: none;
        display: inline-block;
        background-color: #fff;
        border-radius: 7px 7px 0 0;
        padding: 0 20px;
        display: flex;
        align-items: center;
    }

    .active {
        font-family: 'Roboto';
        max-width: 700px;
        float: left;
    }

    .active h5 {
        padding: 10px;
        color: #444;
        font-size: 18px;
        font-weight: 500;
        margin: 0;
    }

    .active h6 {
        font-size: 10px;
        line-height: 2px;
        color: #fff;
    }

    .header-icons span {
        line-height: 70px;
        cursor: pointer;
        font-size: 14px;
    }

    .header-icons .fa {
        cursor: pointer;
        margin: 10px;
    }

    .msg-page {
        height: 516px;
        overflow-y: auto;
        padding-bottom: 50px;
    }

    .input-group input {
        height: 100%;
        background: #fff;
    }

    .input-group ::placeholder {
        color: #C0C0C0 !important;
    }


    .input-group .fa {
        color: #007bff;
        float: right;
    }

    .chat-page {
        position: relative;
    }

    nav .spinner {
        margin-left: 15px;
        width: 64px;
    }

    .empty-chat-sub-title {
        text-align: center;
        color: #555;
        font-size: 18px;
    }

    .loading-messages-container {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        margin-top: 150px;
    }

    .loading-text {
        font-family: 'Roboto';
        color: #1546dc;
        font-size: 25px;
        margin-top: 20px;
    }

    .loading-messages-container .spinner svg {
        stroke: #CCD7F0;
    }

    @media (max-width: 450px) {
        .login-container {
            padding: 40px;
        }
        .outgoing-chats-img {
            display: none;
        }
        .received-chats-img {
            display: none;
        }
        .received-msg-inbox p {
            left: 0;
            width: 100%;
        }
        .received-msg-inbox p:after {
            display: none;
        }
        .received-msg-inbox {
            width: 100%;
        }
        .received-msg {
            width: 100%;
        }
        .outgoing-chats-msg {
            width: 100%;
        }
        .outgoing-chats-msg p {
            width: 100%;
        }
        .outgoing-chats-msg p::before {
            display: none;
        }
        .welcome-message {
            display: none;
        }
    }

    .booker {
        display: flex;
        flex-direction: column;
        flex: 1;
    }

</style>