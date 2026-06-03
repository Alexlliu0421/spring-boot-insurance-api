package com.insurance.dto;

import java.time.LocalDateTime;

// 提示：修改時前端能傳的欄位
// 不包含：applicationId（從 URL 取）、submissionTime、createdBy（不能改）

public class PolicyApplicationUpdateDTO {

    // 提示：修改時通常改哪些欄位？
    // 1. 申請狀態
    // 2. 審核人
    // 3. 審核時間
    // 4. 拒絕原因

    private String status;
    private String reviewedBy;

    private String rejectionReason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    // 提示：需要 Getter/Setter
}