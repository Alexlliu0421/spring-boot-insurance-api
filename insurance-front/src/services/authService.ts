// authService.ts 應該長這樣（很簡單！）
import { http } from './http'

export function login(account: string, password: string) {
    return http.post('/api/auth/login', { account, password })
    // 只負責發出請求，回傳 Promise
    // 不管 token 怎麼存、不管導頁面、不管 alert
}