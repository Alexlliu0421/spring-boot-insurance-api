package com.insurance.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

// 每個帶 JWT token 的請求進來，這個 Filter 負責驗證 token 並告訴 Spring Security 是誰發出的，讓後續的 .authenticated() 檢查通過！

@Component
// 告訴 Spring 這是一個元件，啟動時自動建立物件放進容器，讓其他地方可以注入使用！告訴 Spring
// 這是一個元件，啟動時自動建立物件放進容器，讓其他地方可以注入使用！
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // 繼承OncePerRequestFilter就能保證 JWT 驗證邏輯每個請求只跑一次，不會重複執行！
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    // 覆寫 OncePerRequestFilter 的 doFilterInternal 方法！
    // 覆寫 OncePerRequestFilter 的核心方法
    // 接收 HttpServletRequest request（進來的請求）、HttpServletResponse
    // response（要回傳的回應）、FilterChain filterChain（過濾器鏈）
    // 處理完後呼叫 filterChain.doFilter() 傳給下一個 Filter
    // 若發生錯誤則拋出 ServletException 或 IOException！
    // ServletException → Servlet 在處理 HTTP 請求過程中發生的例外
    // 例如 Filter 或 Servlet 初始化失敗、請求處理邏輯出錯等！
    // IOException → 讀寫資料時的例外（例如網路中斷）
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 提示1：從 header 取出 Authorization
        String header = request.getHeader("Authorization");

        // 提示2：檢查 header 是否有值且以 Bearer 開頭
        if (header != null && header.startsWith("Bearer ")) {

            // 提示3：取出 token（去掉 "Bearer " 前綴）
            String token = header.substring(7);

            // 提示4：驗證 token
            if (jwtTokenProvider.validateToken(token)) {

                // 提示5：取出 username
                String username = jwtTokenProvider.getUsername(token);
                // 提示6：設定 Spring Security 認證狀態
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                        null, new ArrayList<>());
                // UsernamePasswordAuthenticationToken → Spring Security 的認證物件，代表已驗證的使用者身份
                // username → 從 JWT token 取出的使用者名稱
                // null → 密碼（JWT 驗證不需要，所以 null）
                // new ArrayList<>() → 權限列表（目前沒有設定權限，空的）
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // 流程：
                // SecurityContextHolder.getContext() → 取出目前請求的 SecurityContext（認證狀態容器）
                // 設定前：SecurityContext = { authentication: null } → 視為未登入
                // ↓
                // .setAuthentication(authentication) → 把含有 username 的認證物件存進去
                // 設定後：SecurityContext = { authentication:
                // UsernamePasswordAuthenticationToken(username) } → 已登入
                // ↓
                // Spring Security 之後檢查 .authenticated() 時，發現已有認證物件
                // ↓
                // 放行請求，不會回傳 403
            }
        }

        // 提示7：放行請求
        filterChain.doFilter(request, response);
    }
}