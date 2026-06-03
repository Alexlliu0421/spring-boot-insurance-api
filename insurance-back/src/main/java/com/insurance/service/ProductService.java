// InsuredPersonService.java
package com.insurance.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insurance.entity.Product;
// 提示：import 跟 PolicyApplicationService 一樣，只換 Entity
// IService 是 MyBatis-Plus 提供的 Service 介面
// 內建 save()、getById()、list()、updateById()、removeById() 等方法
public interface ProductService extends IService<Product> {

}