package com.insurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.insurance.security.JwtAuthenticationFilter;

import java.util.Arrays;

import org.springframework.http.HttpMethod;

// Spring Security 設定檔，控制哪些 API 需要登入才能存取
@Configuration // 告訴 Spring 這個 class 裡有 @Bean，啟動時來這裡建立物件放進容器
@EnableWebSecurity // 啟用 Web 安全設定，讓 SecurityFilterChain 的設定生效
public class SecurityConfig {

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean // 把回傳物件放進容器統一管理，需要時自動注入
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // SecurityFilterChain → Spring Security 提供的過濾器介面，每個請求進來都會先過這裡決定放行還是擋掉
                // HttpSecurity http → Spring Security 提供的設定工具，pom.xml 加了 security 套件後 Spring
                // 自動建立並傳入
                // throws Exception → 設定過程可能出錯，宣告可能拋出例外交給上層處理
                // Spring 啟動時執行此方法，產生 SecurityFilterChain 放進容器
                // 之後每個請求進來自動攔截，決定放行或擋掉
                http
                                .cors() // 啟用 CORS，使用下面定義的 corsConfigurationSource Bean
                                .and() //// 連接下一個設定
                                .csrf().disable()
                                // csrf() → 取得 CSRF 設定物件
                                // disable() → 關閉 CSRF 保護
                                // 關閉原因：REST API 用 JWT 驗證已足夠，預設開啟，不關的話 POST/PUT/DELETE 會被擋掉回傳 403
                                .authorizeHttpRequests(auth -> auth
                                                // authorizeHttpRequests() → 設定 HTTP 請求授權規則的方法
                                                // auth → lambda 參數，是設定工具，用來一條一條加規則
                                                .antMatchers("/api/auth/**").permitAll()
                                                // antMatchers() → 路徑匹配方法，** 是萬用字元代表後面任何路徑
                                                // permitAll() → 符合路徑的請求全部放行，不需要登入
                                                .antMatchers("/swagger-ui/**").permitAll() // Swagger 開放
                                                .antMatchers("/v3/api-docs/**").permitAll() // Swagger docs 開放
                                                // 注意：Swagger 頁面雖然開放，但頁面內呼叫的 API 還是需要帶 token
                                                // 所以需要在 Swagger 點 Authorize 設定 token 才能呼叫受保護的 API
                                                .anyRequest().authenticated()
                                // anyRequest() → 其他所有路徑
                                // authenticated() → 需要登入（帶 JWT token）才能存取
                                )
                                .sessionManagement(session -> session
                                                // sessionManagement() → Session 管理設定區塊
                                                // session → lambda 參數，是 Session 設定工具
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                // sessionCreationPolicy() → 設定 Session 建立策略的方法
                                // SessionCreationPolicy.STATELESS → 完全不建立也不使用 Session
                                // 原因：JWT token 本身包含身份資訊，不需要伺服器額外記住狀態
                                );
                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                // 把 JwtAuthenticationFilter 加在 UsernamePasswordAuthenticationFilter 之前執行
                // 確保每個請求先驗證 JWT token，再進行後續的 Security 檢查
                return http.build();
                // build() → 把前面所有設定規則打包成 SecurityFilterChain 物件回傳
                // Spring 拿到後套用到每個請求的過濾流程
        }

        @Bean
        // CorsConfigurationSource 是 Spring 提供的介面，定義 CORS 規則
        // corsConfigurationSource() 是方法名稱，Spring 自動找這個名稱來套用設定
        // @Bean 把回傳物件放進容器，.cors() 自動找到它使用！
        public CorsConfigurationSource corsConfigurationSource() {

                // 設定允許前端 5173 存取後端 8080 的 CORS 規則
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // 允許前端來源
                config.setAllowedMethods(Arrays.asList(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.OPTIONS.name())); // 允許的 HTTP 方法
                config.setAllowedHeaders(Arrays.asList("*")); // 允許所有 header
                config.setAllowCredentials(true); // 允許帶 cookie
                //// 建立一個「依 URL 路徑」套用 CORS 規則的物件
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                // 把 config 規則綁定到 /api/** 路徑
                // 只有 /api/ 開頭的請求才套用 CORS 設定
                source.registerCorsConfiguration("/api/**", config); // 包含config
                return source;
        }

}