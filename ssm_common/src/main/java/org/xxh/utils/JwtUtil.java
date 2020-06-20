package org.xxh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class JwtUtil {
    private static String key = "secret";

    public  static  String createJwt(String userId,String userName){
        //默认签发有效期24小时的token 86400000
        return createJwt(userId,userName,"issure",86400000);
    }

    public static String createJwt(String id, String subject, String issure, long till) {
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(key), SignatureAlgorithm.HS256.getJcaName()))
                .setIssuer(issure)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + till));
        return jwtBuilder.compact();
    }
    //    解析
    public static Claims parseJwt(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
        return claims;
    }
}
