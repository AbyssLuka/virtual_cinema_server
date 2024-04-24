package com.luka.r18.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;


public class TokenUtil {

    private static final long EXPIRE_TIME = 60 * 60 * 1000;                                                             //有效时间

    public static String createToken(String username, String password,String userUuid) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        String jwtId = UUID.randomUUID().toString();
        // 附带username信息
        return JWT.create()
                .withJWTId(jwtId)
                .withClaim("username", username)
                .withClaim("userUuid", userUuid)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public static boolean verify(String token, String username, String password) {
        try {
            //根据密码生成JWT效验器
            assert token != null;
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static String getTokenClaim(String token, String key) {
        try {
            assert token != null;
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getUserId(String token) {
        try {
            assert token != null;
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTokenId(String token) {
        try {
            assert token != null;
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getId();
        } catch (Exception e) {
            return null;
        }
    }

    //    获取token过期时间
    public static Date getExpiresAt(String token) {
        try {
            assert token != null;
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (Exception e) {
            return null;
        }
    }

    //是否过期
    public static boolean isExpiresAt(String token) {
        try {
            assert token != null;
            Date expiresAt = getExpiresAt(token);
            Date date = new Date();
            assert expiresAt != null;
            return expiresAt.compareTo(date) < 0;
//        } catch (JWTDecodeException e) {
//            return false;
        }catch (Exception e){
            return false;
        }
    }

    //    获取token签发时间
    public static Date getIssuedAt(String token) {
        try {
            assert token != null;
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }

    }
}
