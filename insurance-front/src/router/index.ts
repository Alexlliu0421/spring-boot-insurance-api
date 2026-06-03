import LoginPage from '../pages/LoginPage.vue'
import DashboardPage from '../pages/DashboardPage.vue'
import PolicyListPage from '../pages/PolicyListPage.vue'
import NotFoundPage from '../pages/NotFoundPage.vue'
import { useAuthStore } from '../stores/auth'
import ClaimsPage from '../pages/ClaimsPage.vue'
// ... 其他兩個
import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            component: LoginPage
            // 提示：不需要 requiresAuth
        },
        {
            path: '/dashboard',
            component: DashboardPage,
            meta: { requiresAuth: true }
            // 這個頁面「需要登入才能進入」
        },
        // 提示：/policies 跟 dashboard 一樣
        {
            path: '/policies',
            component: PolicyListPage,
            meta: { requiresAuth: true }

        },
        // 提示：/:pathMatch(.*)*  → NotFoundPage
        {
            path: '/:pathMatch(.*)*',
            component: NotFoundPage
        },
        {
            path: '/claims',
            component: ClaimsPage,
            meta: { requiresAuth: true }
        }

    ]
})
// router/index.ts：Vue Router 設定檔
// 主要功能：
// 1. 定義路由 → 哪個 URL 對應哪個頁面元件
// 2. meta: { requiresAuth: true } → 標記哪些頁面需要登入才能進入
// 3. route guard（beforeEach）→ 每次切換頁面前檢查是否已登入，未登入導回 /login
// 白話：就像保全，管理哪些頁面可以進、哪些要有登入才能進！
// Route Guard：每次切換頁面前執行，保護需要登入才能進入的頁面
// router.beforeEach() → Vue Router 提供的導航守衛，每次換頁前觸發
router.beforeEach((to) => {
    const authStore = useAuthStore()

    // to.meta.requiresAuth → 這個頁面需要登入
    // !authStore.isAuthenticated → 目前沒有登入
    // 兩個都成立 → alert + 導回 /login
    if (to.meta.requiresAuth && !authStore.isAuthenticated) {
        if (to.path !== '/login') {
            alert('請先登入！')
        }
        return { path: '/login' }
    }
    // 其他情況（不需要登入 或 已經登入）→ 直接放行
})




export default router