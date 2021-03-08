package com.dong.server.config.secutity;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
@Component
public class JwtTokenUtil {
    //荷载claim的名称
    private static final String CLAIM_KEY_USERNAME = "sub";
    //荷载的创建时间
    private static final String CLAIM_KEY_CREATED = "created";
    // jwt令牌的秘钥
    @Value("${jwt.secret}")
    private String secret;
    //jwt的实效时间
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @Author:     dong
     * @Description: 根据用户信息生成token
     * @params [userDetails]
     * @return java.lang.String
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @Author:     dong
     * @Description:  通过用户名获取token
     * @params [token]
     * @return java.lang.String
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims=getClaimsFormToken(token);
            username=claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }

    /**
     * @Author:     dong
     * @Description:  验证token是否有效
     * @params [token, userDetails]
     * @return boolean
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    /**
     * @Author:     dong
     * @Description:  判断token能否刷新
     * @params
     * @return
     */
    public  boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims=getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);

    }

    private boolean isTokenExpired(String token) {
        Date expireDate=getExpireDateFromToken(token);
        return expireDate.before(new Date());

    }

    private Date getExpireDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }


    private Claims getClaimsFormToken(String token) {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        };
        return claims;
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();


    }

    private Date generateExpiration() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }
}
