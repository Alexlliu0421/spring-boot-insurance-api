package com.insurance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // 告訴 Swagger 所有 API 預設都需要 Bearer token
                .addSecurityItem(new SecurityRequirement().addList("Bearer"))
                .components(new Components()
                        // 新增一個叫 "Bearer" 的安全機制，讓 Swagger 頁面出現 🔒 Authorize 按鈕
                        .addSecuritySchemes("Bearer", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP) // 類型是 HTTP
                                .scheme("bearer") // 使用 bearer 格式
                                .bearerFormat("JWT") // token 格式是 JWT
                        ));
    }
}