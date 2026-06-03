package com.insurance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// 提示：只包含前端能傳的欄位
// 不包含：applicationId、submissionTime、applicationStatus（系統自動產生）

public class PolicyApplicationReqDTO {

    // 提示：要保人資料
    private String nationalId;
    private String applicantName;
    private String applicantGender;
    private LocalDate applicantBirthdate;  // 日期用什麼型態？
    private String relationshipToInsured;

    // 提示：商品資料
    private String productCode;
    private BigDecimal sumInsured;   // 金額用什麼型態？
    private BigDecimal annualPremium; //這是年繳保費

    private String contactPhone;
     private String createdBy;
    public String getNationalId() {
        return nationalId;
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
   

    // 提示：需要 Getter/Setter
}