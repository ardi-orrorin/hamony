import './assets/global.css'
import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import {createPinia} from "pinia";
import {useToken} from "@/store/member";

const app = createApp(App)
const pinia = createPinia()

app.use(router)
app.use(pinia)

app.mount('#app')
