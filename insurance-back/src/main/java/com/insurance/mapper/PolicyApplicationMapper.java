package com.insurance.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
// 2. BaseMapper
import com.insurance.entity.PolicyApplication;

// ===================== Mapper 通用說明 =====================
// 職責：定義資料庫操作方法，執行 SQL，回傳結果給 ServiceImpl
// 
// @Mapper          → MyBatis 自動產生實作類別，放進 Spring 容器
// BaseMapper<T>    → 繼承後自動擁有內建 CRUD 方法，不需要寫 SQL
//                    selectById() / selectList() / insert() / updateById() / deleteById()
// 
// 呼叫者：ServiceImpl 透過 baseMapper 呼叫
// 
// 自訂 SQL：
//   1. 在介面定義方法
//   2. 在對應的 Mapper.xml 寫 SQL
//   3. MyBatis 透過方法名稱（id）對應 XML 執行
// ==========================================================
@Mapper

// 提示：加上什麼 annotation 讓 MyBatis 知道這是 Mapper？
public interface PolicyApplicationMapper extends BaseMapper<PolicyApplication> {
    // 自訂新增，新增後回填 applicationId
    void insertApplication(PolicyApplication policyApplication);

    // 自訂修改狀態
    void updateApplicationStatus(PolicyApplication policyApplication);
}
