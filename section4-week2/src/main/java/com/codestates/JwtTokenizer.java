package com.codestates;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class JwtTokenizer {

    // 1
    public String encodeBase64SecretKey(String secretKey){
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 2
    public String generateAccessToken(Map<String ,Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey){

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return  Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();

    }

    //3
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey){

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return  Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();

    }

    // 4
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey){
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;

    }

    public void verifySignature(String jws , String base64EncodedSecretKey){

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

    }





}
