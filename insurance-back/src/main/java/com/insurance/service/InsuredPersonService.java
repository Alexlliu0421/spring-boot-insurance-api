package com.insurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insurance.entity.InsuredPerson;

// 繼承 IService，自動擁有 list()、save()、getById()、updateById()、removeById() 等內建方法
// 不需要自己定義，MyBatis-Plus 已經幫你寫好了
public interface InsuredPersonService extends IService<InsuredPerson> {
    // 若有自訂業務邏輯才在這裡定義，目前沒有
}