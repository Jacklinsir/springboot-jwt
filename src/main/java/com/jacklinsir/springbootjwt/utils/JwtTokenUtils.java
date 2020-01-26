package com.jacklinsir.springbootjwt.utils;

import com.jacklinsir.springbootjwt.model.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt工具类
 *
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 14:12
 */
@Component
@Slf4j
public class JwtTokenUtils {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";


    /**
     * JWT加解密使用的密钥
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * jwt过期时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;
    /**
     * JWT存储的请求头
     */
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 根据用户生成toke
     */
    public String generateToken(SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put(CLAIM_KEY_USERNAME, user.getUsername());
        map.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(map);
    }


    /**
     * 负责生成token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 负责生成jwt过期时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败：{}", token);
        }
        return claims;
    }

    /**
     * 校验token是否过期
     *
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 当原来的token没过期是可以刷新
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (canRefresh(token)) {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
        return null;
    }

    /**
     * 判断token是否可以被刷新
     */
    private boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }


    /**
     * 验证token是否还有效
     *
     * @param token 客户端传入的token
     * @param user  从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, SysUser user) {
        String username = getUserNameFromToken(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }


    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


}
