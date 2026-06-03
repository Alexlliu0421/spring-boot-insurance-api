package com.insurance.controller;

import com.insurance.common.ApiResponse;
import com.insurance.common.ResultCode;
import com.insurance.dto.LoginReqDTO;
import com.insurance.entity.User;
import com.insurance.security.JwtTokenProvider;
import com.insurance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserMapper userMapper;

@PostMapping("/login")
public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginReqDTO loginReqDTO) {
    
    // 提示：查資料庫
    User user = userMapper.findByUsername(loginReqDTO.getAccount());
    
    // 提示：查不到 或 密碼不符 → 401
    if (user == null || !user.getPassword().equals(loginReqDTO.getPassword())) {
        return ResponseEntity.ok(ApiResponse.error(ResultCode.UNAUTHORIZED));
    }
    
    // 提示：正確 → 產生 token
    String token = jwtTokenProvider.generateToken(user.getUsername());
    return ResponseEntity.ok(ApiResponse.success(token));
}
}