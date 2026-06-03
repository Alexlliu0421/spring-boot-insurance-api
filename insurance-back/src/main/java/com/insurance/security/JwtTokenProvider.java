package com.insurance.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    // 提示：用 @Value 從 application.yml 讀取
    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration-seconds}")
    private long expirationSeconds;

    // 提示：產生 token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 存入使用者名稱
                .setIssuedAt(new Date()) // 記錄發行時間（現在）
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000)) // 過期時間（現在 +
                                                                                                // expirationSeconds）
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                // .signWith是jjwt 提供的方法，用來對 token 進行簽名，接收 Key 和演算法兩個參數！
                // 指定用 UTF-8 編碼把字串轉成 byte 陣列，確保跨環境結果一致！
                .compact();
        // 產生最終 JWT 字串（eyJhbGci.eyJzdWIi.簽名）
    }

    // 提示：從 token 取出 username
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                // 設定簽名密鑰把字串轉成 byte 陣列，用來驗證 token 簽名是否正確
                .build()
                // 把以上設定組合成 JwtParser 物件
                .parseClaimsJws(token)
                // 解析 token，驗證簽名是否正確
                .getBody()
                // 取出 token 的 payload（存放 username、時間等資料）
                .getSubject();
        // 取出 generateToken 時用 setSubject(username) 存入的使用者名稱
    }

    // 提示：驗證 token
    // try-catch：parseClaimsJws 驗證失敗會直接噴錯，用 try-catch 把錯誤接住，統一回傳 false！

    public boolean validateToken(String token) {
        // 解析 token，驗證簽名和過期時間
        // 成功 → token 合法，回傳 true
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 簽名不符或已過期 → token 無效
            return false;
        }
    }
}