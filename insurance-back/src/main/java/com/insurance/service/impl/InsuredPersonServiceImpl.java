package com.insurance.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insurance.entity.InsuredPerson;
import com.insurance.mapper.InsuredPersonMapper;
import com.insurance.service.InsuredPersonService;

@Service // 告訴 Spring 幫我建立這個物件，放進容器
public class InsuredPersonServiceImpl
        extends ServiceImpl<InsuredPersonMapper, InsuredPerson>
        // ServiceImpl 內建 baseMapper，自動擁有 CRUD 方法
        // InsuredPersonMapper → 操作資料庫的 Mapper
        // InsuredPerson → 對應的 Entity
        implements InsuredPersonService { // 實作 InsuredPersonService 介面

    // 目前沒有自訂業務邏輯，直接使用 ServiceImpl 內建的 CRUD 方法
}
