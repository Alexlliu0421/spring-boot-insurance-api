package com.insurance.service.impl;

// 提示：仿照 InsuredPersonServiceImpl，只換 Entity、Mapper、Service

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insurance.entity.Product;
import com.insurance.service.ProductService;
import com.insurance.mapper.ProductMapper;

@Service
public class ProductServiceImpl
        extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

}