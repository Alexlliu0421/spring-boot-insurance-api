import axios from 'axios'
import { useAuthStore } from '../stores/auth'


// 建立 axios instance
// 建立 axios 實體，命名為 http，設定基底網址為 localhost:8080
// 之後呼叫 API 只需填路徑，例如 http.get('/api/policy-application/list')
// export 讓其他檔案可以 import 使用
export const http = axios.create({
    baseURL: 'http://localhost:8080',
})

// request interceptor：每次發出請求前，自動把 token 加到 header，讓後端知道是誰在呼叫 API
http.interceptors.request.use((config) => {
    // config → 這次請求的設定（url、headers、body 等）
    const authStore = useAuthStore()
    // 取得 Pinia auth store（stores/auth.ts），存有目前的 token

    // header 還沒有 Authorization 且 token 有值 → 加上去
    if (!config.headers.Authorization && authStore.token) {
        // Bearer token 格式：後端 JWT 驗證的標準格式
        config.headers.Authorization = `Bearer ${authStore.token}`
    }

    return config // 放行請求，讓它繼續發出去
})

// response interceptor
// 提示：收到 401 時，自動登出並導回登入頁
// response interceptor：攔截每一個後端回應
// axios 的 .use() 接收兩個函數，根據 HTTP status 決定執行哪個
// 成功（2xx）→ 直接放行，失敗（4xx/5xx）→ 統一處理錯誤
http.interceptors.response.use(
    (response) => response, // 成功回應，直接放行
    (error) => {            // 失敗回應，axios 將錯誤包裝成 error 物件
        // error.response?.status → 取出 HTTP 狀態碼
        // ?. 是因為網路斷線時 error.response 可能是 undefined
        if (error.response?.status === 401) {
            // 401 未授權 → token 過期或無效，清除登入狀態並導回登入頁
            const authStore = useAuthStore()
            authStore.logout()
            window.location.href = '/login'
        }
        return Promise.reject(error) // 其他錯誤繼續往上拋，讓各頁面自行處理
    }
)