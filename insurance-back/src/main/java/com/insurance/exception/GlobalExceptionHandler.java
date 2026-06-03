package com.insurance.exception;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.insurance.common.ApiResponse;
import com.insurance.common.ResultCode;

@RestControllerAdvice // 全域例外處理器，統一將錯誤以 ApiResponse 格式回傳給前端
public class GlobalExceptionHandler {

    // 找不到資料時拋出，回傳 404
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NoSuchElementException e) {
        return ResponseEntity.status(404).body(ApiResponse.error(ResultCode.NOT_FOUND));
    }

    // 參數錯誤時拋出，回傳 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequestException(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(ApiResponse.error(ResultCode.BAD_REQUEST));
    }

    // 兜底，其他所有例外都回傳 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {
         e.printStackTrace(); // 加這行
        return ResponseEntity.status(500).body(ApiResponse.error(ResultCode.SERVER_ERROR));
    }
}