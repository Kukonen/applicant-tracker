import { createApp } from 'vue'
import './assets/styles/styles.scss'
import App from './App.vue'
// import 'ant-design-vue/dist/antd.css';

import Antd from 'ant-design-vue';

const app = createApp(App);

app.use(Antd);
app.mount('#app')
