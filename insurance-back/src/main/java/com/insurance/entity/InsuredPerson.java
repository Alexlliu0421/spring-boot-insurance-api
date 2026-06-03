package com.insurance.entity;

// 提示：需要哪些 import？


import com.baomidou.mybatisplus.annotation.TableName;

// 1. TableId、TableName
// 2. LocalDate

import java.time.LocalDate;
// 3. lombok.Data

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@TableName("insured_person")
// 提示：對應資料庫哪張表？
public class InsuredPerson {

    // 提示：主鍵
    @TableId
    private String applicationId;
    private String insuredIdNo;
    private String insuredName;
    private String insuredGender;

    // 提示：日期用什麼型態？
    private LocalDate insuredBirthdate;
}
