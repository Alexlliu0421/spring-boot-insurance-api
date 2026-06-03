import { defineStore } from 'pinia'
import{ ref, computed } from 'vue'
// 1. defineStore → pinia
// 2. ref、computed → vue

// Pinia auth store：統一管理登入狀態
// 功能：
// 1. token → 存放 JWT token（ref 響應式，重整從 localStorage 還原）
// 2. isAuthenticated → 判斷是否已登入（token 有值 → true）
// 3. login() → 存 token 到 ref 和 localStorage
// 4. logout() → 清除 token
// 讓 router、service、頁面都能共享同一份登入狀態
export const useAuthStore = defineStore('auth', () => {
    
    // 提示：token 狀態，初始值從 localStorage 取
    const token = ref<string | null>(localStorage.getItem('token'))
    
    // 提示：computed，token 有值就代表已登入
    const isAuthenticated = computed(() => !!token.value)
    
    // 提示：login 方法，存 token 到 ref 和 localStorage
    function login(newToken: string) {
        token.value = newToken
        localStorage.setItem('token', newToken)
    }
    
    // 提示：logout 方法，清除 token
    function logout() {
        token.value = null
        localStorage.removeItem('token')
    }
    
    return { token, isAuthenticated, login, logout }
})