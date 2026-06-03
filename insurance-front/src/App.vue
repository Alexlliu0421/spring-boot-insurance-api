<script setup lang="ts">
import { useAuthStore } from './stores/auth'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const router = useRouter()

function logout() {
  authStore.logout()
  router.push('/login')
}
//這樣登出後會導回登入頁
</script>

<template>
  <q-layout view="hHh lpR fFf">

    <!-- 頂部導覽列，只在登入後顯示 -->
    <q-header v-if="authStore.isAuthenticated" elevated class="bg-primary">
      <q-toolbar>
        <q-toolbar-title>保單管理系統</q-toolbar-title>
        <q-btn flat label="首頁" to="/dashboard" />
        <q-btn flat label="投保申請" to="/policies" />
        <q-btn flat label="理賠進度" to="/claims" />
        <q-btn flat label="登出" @click="logout" />
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>


<style scoped></style>
