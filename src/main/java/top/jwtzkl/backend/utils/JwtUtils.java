package top.jwtzkl.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "jwtzkljwtzkl";
    private static Long expire = 43200000L;

    /**
     * 生成jwt令牌
     * @param data  存储的数据   jwt第二部分 payload中的内容
     * @return
     */
    public static String generateJwt(Map<String,Object> data) {
        String jwt = Jwts.builder()
                .addClaims(data)
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }


}
