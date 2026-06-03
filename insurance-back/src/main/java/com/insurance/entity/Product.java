package com.insurance.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

// 提示：需要哪些 import？
// 提示：對應資料庫哪張表？
@TableName("product")
@Data
public class Product {

    // 提示：主鍵，對應 product_code 欄位
    @TableId("product_code")
    private String code;

    private String productName;
    private Integer minAge;
    private Integer maxAge;

    // 提示：金額用什麼型態？
    private BigDecimal minSumInsured;
    private BigDecimal maxSumInsured;
    // int/long     → 整數，沒有小數點
    // double/float → 有小數點，但不精確
    // BigDecimal   → 有小數點，精確，金融系統必用！
}
