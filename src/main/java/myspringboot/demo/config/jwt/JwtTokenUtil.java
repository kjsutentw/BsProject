package myspringboot.demo.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import myspringboot.demo.service.SysPermissionService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {

    /***
     * 进行签名的秘钥
     */
    private String secret;
    private Long expiration;
    private String header;

    /**
     * 生成token令牌
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>(2);
        claims.put("sub",userDetails.getUsername());
        claims.put("created",new Date());
        return generateToken(claims);
    }

    public String generateToken(Map<String, Object> claims) {
        Date expirationDate =new Date(System.currentTimeMillis()+ 2592000L * 1000);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }


    /**
     * 从令牌中获取username
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        String username;
        try{
            Claims claims=getClaimsFromToken(token);
            username=claims.getSubject();
        }catch (Exception e){
            username = null;
        }

        return username;

    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try{
            claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * 解析token判断是否过期
     * @param token
     * @return
     */
    public Boolean IsTokenExpired(String token){
        try{
            Claims claims=getClaimsFromToken(token);
            Date expiration =claims.getExpiration();
            return new Date().before(expiration);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 刷新令牌  （更新令牌时间）
     * @param token
     * @return
     */
    public String refreshToken(String token){
        String refreshedToken;
        try{
            Claims claims=getClaimsFromToken(token);
            claims.put("created",new Date());
            refreshedToken=generateToken(claims);
        }catch (Exception e){
            refreshedToken =null;
        }
        return refreshedToken;
    }


    /**
     * 验证令牌
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token,UserDetails userDetails){
        String username =getUsernameFromToken(token);
        Boolean bo=false;

        if(username.equals(userDetails.getUsername())&&IsTokenExpired(token)){
           bo=true;
        }

        return (bo);
    }


}
