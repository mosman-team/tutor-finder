import Vue from 'vue';
import App from 'views/App.vue';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import vuetify from 'plugins/vuetify' // path to vuetify export
import store from './store';
import { router } from './router';
import {connect} from "./utils/ws";

let user = JSON.parse(localStorage.getItem('user'));

if (user !== null){
    connect();
}

import {
    faHome,
    faUser,
    faUserPlus,
    faSignInAlt,
    faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.config.productionTip = false;

Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);


new Vue({
    vuetify,
    store,
    router,
    render: h => h(App)
}).$mount('#app');




















