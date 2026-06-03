package com.insurance.common;

//因為狀態碼是固定不變的常數集合，不會有第 6 個狀態碼突然冒出來，用 enum 可以限定只有這幾個選項，比用 int 或 String 更安全！
//// 所有狀態碼集中在一個地方管理
public enum ResultCode {
    // 1. 常數放最前面
    SUCCESS(200, "成功"),// 常數1
    //建立一個 ResultCode 物件
    // code = 200
    // message = "成功"
    NOT_FOUND(404, "找不到"),// 常數2
    BAD_REQUEST(400, "參數錯誤"),// 常數3
    SERVER_ERROR(500, "伺服器錯誤"),//最後一個用;結尾
    UNAUTHORIZED(401, "未授權");
    

    // 2. 欄位
    private final int code;
    private final String message;

    // 3. 建構子
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 4. 只要 Getter，不要 Setter（final 不能改）
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
