package com.insurance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;

@TableName("policy_application")
public class PolicyApplication {

    // 提示：這是主鍵
    @TableId(type = IdType.AUTO)
    private Integer applicationId;
    private String nationalId;
    private String applicantName;
    private String applicantGender;
    private LocalDate applicantBirthdate;    // 日期
    private String relationshipToInsured;
    private String productCode;
    private BigDecimal sumInsured;           // 金額
    private BigDecimal annualPremium;        // 金額
    private String contactPhone;
    private String applicationStatus;
    private LocalDateTime submissionTime;   // 日期+時間
    private String createdBy;
    private LocalDateTime reviewTime;       // 日期+時間
    private String reviewedBy;
    private String rejectionReason;

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getNationalId() {
        return  nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantGender() {
        return applicantGender;
    }

    public void setApplicantGender(String applicantGender) {
        this.applicantGender = applicantGender;
    }

    public LocalDate getApplicantBirthdate() {
        return applicantBirthdate;
    }

    public void setApplicantBirthdate(LocalDate applicantBirthdate) {
        this.applicantBirthdate = applicantBirthdate;
    }

    public String getRelationshipToInsured() {
        return relationshipToInsured;
    }

    public void setRelationshipToInsured(String relationshipToInsured) {
        this.relationshipToInsured = relationshipToInsured;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public BigDecimal getAnnualPremium() {
        return annualPremium;
    }

    public void setAnnualPremium(BigDecimal annualPremium) {
        this.annualPremium = annualPremium;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
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

    public Integer getApplicationId() {
        return applicationId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

}
