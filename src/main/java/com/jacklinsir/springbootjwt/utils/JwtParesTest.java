package com.jacklinsir.springbootjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 14:06
 */
public class JwtParesTest {

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5OSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTgwMDE5NzA2LCJuaWNrTmFtZSI6Iuezu-e7n-euoeeQhuWRmCJ9.bfVpzYZyowfleEsT9rfxF_7MhyZmueX0Jdn7dXOv2is";
        Claims claims = Jwts.parser().setSigningKey("java").parseClaimsJws(token).getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.get("nickName"));
    }
}
