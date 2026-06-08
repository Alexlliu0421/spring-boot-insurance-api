package com.insurance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insurance.entity.PolicyApplication;
import com.insurance.mapper.InsuredPersonMapper;
import com.insurance.mapper.PolicyApplicationMapper;
import com.insurance.service.PolicyApplicationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
// 需要 import 什麼？
// 1. UUID（產生唯一ID）
// 2. LocalDateTime（取得目前時間）

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PolicyApplicationServiceImpl
        extends ServiceImpl<PolicyApplicationMapper, PolicyApplication>
        implements PolicyApplicationService {
    // ServiceImpl 已經幫你實作了 list()、save()、getById()...
    // ServiceImpl 內部呼叫 baseMapper
    // 你只需要覆寫有業務邏輯的方法，例如 save()
    // 覆寫 save 方法，新增前自動處理
    @Autowired // 注入 InsuredPersonMapper，用來刪除關聯的被保險人資料
    private InsuredPersonMapper insuredPersonMapper;

    // 提示：加在 @Override 上面
    // 確保兩個刪除操作是一個交易
    // 一起成功 或 一起失敗（例如刪 insured_person 成功但刪 policy_application 失敗 → 全部回滾）
    @Override
    @Transactional
    // @Transactional → 確保兩個刪除操作是一個交易，任一步驟拋出例外 → 全部回滾
    public boolean removeById(Serializable id) {
        // 1. 先刪 insured_person，避免違反外鍵約束
        insuredPersonMapper.deleteById(id);
        // 2. 再刪 policy_application，呼叫父類別的 removeById
        return super.removeById(id);
    }

    @Override
    public boolean save(PolicyApplication policyApplication) {

        // 提示2：設定送件時間
        policyApplication.setSubmissionTime(LocalDateTime.now());

        // 提示3：設定初始狀態為 PENDING
        policyApplication.setApplicationStatus("PENDING");

        // 呼叫父類別的 save 存進資料庫
        return super.save(policyApplication);
    }

    @Override
    public boolean updateById(PolicyApplication policyApplication) {
        // 提示：修改時自動記錄審核時間
        policyApplication.setReviewTime(LocalDateTime.now());
        return super.updateById(policyApplication);
    }

    @Override
    public PolicyApplication createApplication(PolicyApplication policyApplication) {
        policyApplication.setSubmissionTime(LocalDateTime.now());
        // 提示1：設定送件時間
        policyApplication.setApplicationStatus("PENDING");
        // 提示2：設定初始狀態 PENDING
        baseMapper.insertApplication(policyApplication);
        // 提示3：呼叫 baseMapper.insertApplication()
        return policyApplication;
        // 提示4：回傳含 applicationId 的 Entity
    }

    @Override
    public void updateStatus(PolicyApplication policyApplication) {
        // 提示：記得設定 reviewTime
        policyApplication.setReviewTime(LocalDateTime.now());
        // 提示：呼叫 baseMapper.updateApplicationStatus()
        baseMapper.updateApplicationStatus(policyApplication);

    }

}
