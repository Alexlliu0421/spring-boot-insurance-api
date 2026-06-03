package com.insurance.dto;

// 登入請求 DTO，只包含帳號和密碼
public class LoginReqDTO {

    // 提示：帳號
    private String account;
    
    // 提示：密碼
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 提示：需要 Getter/Setter
}