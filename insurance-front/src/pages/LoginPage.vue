<template>
    <q-page class="flex flex-center">
        <q-card style="width: 400px">
            <q-card-section class="text-center">
                <div class="text-h5">保單管理系統</div>
                <div class="text-subtitle2 text-grey">請登入以繼續</div>
            </q-card-section>
            <q-card-section>
                <q-input v-model="account" label="帳號" outlined class="q-mb-md" />
                <q-input v-model="password" label="密碼" type="password" outlined />
            </q-card-section>
            <q-card-actions align="right">
                <q-btn color="primary" label="登入" @click="login" style="width: 100%" />
            </q-card-actions>
        </q-card>
    </q-page>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useAuthStore } from '../stores/auth'
import { useRouter } from 'vue-router'

import { login as loginApi } from '../services/authService'
const authStore = useAuthStore()
const router = useRouter()

const account = ref('')
const password = ref('')
async function login() {
    // async/await 寫法：讓非同步程式碼讀起來像同步，比 .then() 巢狀更清楚
    try {
        // await 等待後端回應後才繼續執行，不需要 .then() 串接
        const response = await loginApi(account.value, password.value)
        // loginApi → authService.ts 的函數，只負責發請求回傳 Promise

        if (response.data.code !== 200) {
            // response.data → ApiResponse，code 不是 200 代表帳密錯誤
            alert('登入失敗，請檢查帳號密碼')
            return // 提前結束，不繼續執行
        }
        const token = response.data.data // token 在 ApiResponse 的 data 欄位
        authStore.login(token)           // 存進 Pinia store 和 localStorage
        router.push('/dashboard')        // 導向 Dashboard
    } catch (error) {
        // try/catch 取代 .catch()，攔截網路錯誤等例外情況
        console.error('登入失敗:', error)
        alert('登入失敗，請檢查帳號密碼')
    }
}


</script>

<style scoped></style>