<template>
            <div>
                <div class="received-chats" v-if="currentUserId !== message.user.id">
                    <div class="received-chats-img">
                        <img v-bind:src="message.user.img" alt="" class="avatar">
                    </div>

                    <div class="received-msg">
                        <div class="received-msg-inbox">
                            <p class="ow"><span>{{ message.user.username }}</span><br>{{ message.text }}</p>
                        </div>
                    </div>
                </div>

                <div class="outgoing-chats" v-else>
                    <div class="outgoing-chats-msg">
                        <p class="ow">{{ message.text }}</p>

                        <div class="actn-buttons">
                            <v-btn icon @click="edit" small>
                                <v-icon>
                                    edit
                                </v-icon>
                            </v-btn>
                            <v-btn icon @click="del" small>
                                <v-icon>
                                    delete
                                </v-icon>
                            </v-btn>
                        </div>

                    </div>

                    <div class="outgoing-chats-img">
                        <img v-bind:src="message.user.img" alt="" class="avatar">
                    </div>
                </div>
            </div>

</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        props: ['currentUserId', 'message', 'editMessage'],

        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style scoped>

    @import 'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css';
    @import 'https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css';
    @import url('https://fonts.googleapis.com/css?family=Abril+Fatface&display=swap');
    @import url('https://fonts.googleapis.com/css?family=Roboto&display=swap');

    /*my css*/
    .ow {
        overflow-wrap: break-word;
        word-wrap: break-word;
        hyphens: auto;
    }
    .actn-buttons{
        display: flex;
        justify-content: flex-end;
    }

    h3 {
        font-family: 'Abril Fatface';
        margin-bottom: 20px;
    }

    .avatar {
        width: 37px;
        height: 37px;
    }


    .received-chats {
        padding: 20px;
        display: flex;
        justify-content: flex-start;
    }

    .received-chats-img {
        display: inline-block;
        display: flex;
        align-items: center;
    }

    .received-msg {
        display: inline-block;
        padding: 0;
        vertical-align: top;
    }

    .received-msg-inbox {
        width: 57%;
    }

    .received-msg-inbox p {
        font-family: 'Roboto';
        background: #ffffff none repeat scroll 0 0;
        border-radius: 7px;
        color: #646464;
        font-size: 16px;
        margin: 0;
        padding: 12px;
        position: relative;
        width: 300px;
        min-height: 61px;
        left: 30px;
        box-shadow: 0 2px 2px rgba(0, 0, 0, 0.1);
    }

    .received-msg-inbox p span {
        color: #6889fd;
        text-transform: capitalize;
        font-weight: bold;
        font-size: 14px;
    }

    .received-msg-inbox p:after {
        content: '';
        position: absolute;
        left: 0;
        top: 50%;
        width: 0;
        height: 0;
        border: 10px solid transparent;
        border-right-color: #ffffff;
        border-left: 0;
        margin-top: -10px;
        margin-left: -10px;
        transform: skew(0, 30deg);
    }

    #triangle-right {
        width: 0;
        height: 0;
        border-top: 50px solid transparent;
        border-left: 100px solid red;
        border-bottom: 50px solid transparent;
    }

    .time {
        color: #777;
        display: block;
        font-size: 12px;
        margin: 8px 0 0;
    }

    .outgoing-chats {
        overflow: hidden;
        margin: 26px 20px;
        display: flex;
        justify-content: flex-end;
    }

    .outgoing-chats-msg p {
        font-family: 'Roboto';
        background: #80DEEA none repeat scroll 0 0;
        color: #fff;
        font-size: 16px;
        margin: 0;
        color: #fff;
        padding: 12px;
        max-width: 300px;
        min-height: 42px;
        position: relative;
        border-radius: 7px;
        align-items: center;
        line-height: 1.2;

        /*253*/
    }

    .outgoing-chats-msg p::before {
        content: '';
        position: absolute;
        right: 0;
        top: 50%;
        width: 0;
        height: 0;
        border: 10px solid transparent;
        border-left-color: #80DEEA;
        border-right: 0;
        margin-top: -10px;
        margin-right: -10px;
        transform: skew(0, -30deg);
    }

    .outgoing-chats-img {
        display: flex;
        align-items: center;
        padding-top: 10px;
        margin-left: 20px;
    }

    .input-group input {
        height: 100%;
        background: #fff;
    }

    .input-group ::placeholder {
        color: #C0C0C0 !important;
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
    }

</style>