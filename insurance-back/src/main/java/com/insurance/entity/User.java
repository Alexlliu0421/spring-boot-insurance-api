package com.insurance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

// 提示：對應資料庫 users 表
// 提示：@TableId(type = IdType.AUTO)
// 提示：欄位有 id、username、password
// 提示：需要 Getter/Setter
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}