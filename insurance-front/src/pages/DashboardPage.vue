<template>
    <q-page class="flex flex-center">
        <div class="text-center">
            <q-icon name="home" size="80px" color="primary" />
            <h4 class="q-mt-md q-mb-sm">歡迎回來，admin！</h4>
            <p class="text-grey">請使用上方導覽列切換頁面</p>
        </div>
    </q-page>
</template>
<script setup lang="ts">
// 提示：需要 import 什麼？
import { useAuthStore } from '../stores/auth'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const router = useRouter()

function logout() {
    authStore.logout()       // 清除 token
    router.push('/login')    // Router的 push 方法導回登入頁
}
</script>