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
import { http } from '../services/http'

const authStore = useAuthStore()
const router = useRouter()

const account = ref('')
const password = ref('')

async function login() {

    // 提示：呼叫 POST /api/auth/login
    http.post('/api/auth/login', { account: account.value, password: password.value })
        //// 發出 POST 請求到後端 AuthController 的 /api/auth/login
        // { account, password } → 對應後端 LoginReqDTO 的欄位
        .then(response => {
            if (response.data.code !== 200) {
                alert('登入失敗，請檢查帳號密碼')
                return
            }
            const token = response.data.data
            authStore.login(token)
            router.push('/dashboard')
        })
        .catch(error => {
            console.error('登入失敗:', error)
            alert('登入失敗，請檢查帳號密碼')
        })


}
</script>

<style scoped></style>