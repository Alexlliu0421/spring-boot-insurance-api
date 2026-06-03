package com.insurance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.insurance.entity.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

// 提示：繼承 BaseMapper<User>
// 提示：@Mapper
// 提示：加一個自訂方法：依 username 查詢
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);
}