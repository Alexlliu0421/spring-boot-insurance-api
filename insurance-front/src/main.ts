import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import router from './router'
import { Quasar } from 'quasar'
import '@quasar/extras/material-icons/material-icons.css'
import 'quasar/src/css/index.sass'

const app = createApp(App)
const pinia = createPinia()

app.use(pinia)   // 先裝 pinia
app.use(router)  // 再裝 router
app.use(Quasar, {
    plugins: {}
})

app.mount('#app')