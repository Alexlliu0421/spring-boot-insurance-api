import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
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


    const username = computed(() => {
        if (!token.value) return ''
        const payload = token.value.split('.')[1]!
        // split('.') → 把 JWT 用 . 分割成三段陣列 [header, payload, signature]
        // [1] → 取第二段 payload（Base64 編碼的使用者資訊）
        // ! → 非空斷言，告訴 TypeScript 這裡一定有值（JWT 一定有三段）
        return JSON.parse(atob(payload)).sub
        // atob() → 瀏覽器內建函數，把 Base64 字串解碼成 JSON 字串
        //           例：'eyJzdWIiOiJhZG1pbiJ9' → '{"sub":"admin","iat":...}'
        // JSON.parse() → 把 JSON 字串轉成物件 → { sub: "admin", iat: ... }
        // .sub → 取出後端 setSubject(username) 存入的使用者名稱
    })


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