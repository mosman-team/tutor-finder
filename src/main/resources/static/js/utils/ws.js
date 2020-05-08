import SockJS from 'sockjs-client'
import {Stomp} from "@stomp/stompjs";

let stompClient = null;
const handlers = {};

export function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);

    stompClient.debug = () => {}; // logging turned off

    stompClient.connect({}, frame => {
        // console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/activity', message => {
            handlers.add(JSON.parse(message.body));
        });
    });
}

export function addHandler(handler) {
    handlers.add = handler
}


export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}






