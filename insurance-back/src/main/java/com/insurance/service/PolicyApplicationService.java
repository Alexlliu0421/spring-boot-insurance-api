package com.insurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insurance.entity.PolicyApplication;

// IService 是 MyBatis-Plus 提供的 Service 介面
// 內建 save()、getById()、list()、updateById()、removeById() 等方法
public interface PolicyApplicationService extends IService<PolicyApplication> {
    // 自訂新增，回傳含 applicationId 的 Entity
    PolicyApplication createApplication(PolicyApplication policyApplication);

    // 自訂修改狀態
    void updateStatus(PolicyApplication policyApplication);
}