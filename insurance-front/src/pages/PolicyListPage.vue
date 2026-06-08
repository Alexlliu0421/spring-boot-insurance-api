<template>
    <q-page class="q-pa-md">
        <div class="text-h5 q-mb-md">投保申請列表</div>
        <q-table :rows="policies" :columns="columns" row-key="applicationId" flat bordered />
        <!-- q-table 內建分頁功能，預設每頁 5 筆，超過就自動產生翻頁按鈕，不需要自己寫！ -->
    </q-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { http } from '../services/http'
import { getList } from '@/services/policyService'

const policies = ref<any[]>([])

const columns = [
    { name: 'applicationId', label: '申請編號', field: 'applicationId', align: 'left' as const },
    { name: 'applicantName', label: '要保人', field: 'applicantName', align: 'left' as const },
    { name: 'productCode', label: '商品代碼', field: 'productCode', align: 'left' as const },
    { name: 'sumInsured', label: '保額', field: 'sumInsured', align: 'right' as const },
    { name: 'applicationStatus', label: '狀態', field: 'applicationStatus', align: 'center' as const },
]
//Quasar 的 q-table 自動產生表格和分頁，只要傳入 rows 和 columns 就好！

onMounted(async () => {
    // onMounted → Vue 生命週期鉤子，頁面載入完成後執行
    // async → 讓裡面可以使用 await
    const response = await getList()
    // await 等待後端回應後才繼續執行
    // getList() → policyService.ts 的函數，只負責發請求回傳 Promise
    policies.value = response.data.data
    // response.data → ApiResponse
    // response.data.data → 真正的資料陣列
    // 存進 policies，q-table 偵測到變化自動更新畫面
})
//頁面一載入就自動呼叫 API 取資料，存進 policies，q-table 偵測到資料變了就自動更新畫面！
</script>