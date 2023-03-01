package com.technicalAssessment.employeeApplication.utils;

import com.technicalAssessment.employeeApplication.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static String key ="This_is_my_key";
    private static long expiryDuration = 60*60;

    public String generateJwt(User user){
        Date issuedAt = new Date();
        long msTime = System.currentTimeMillis();
        long expiryTime = msTime + expiryDuration *1000;
        Date expiryAt = new Date(expiryTime);

        Claims claims= Jwts.claims()
                .setIssuer(String.valueOf(user.getUserId()))
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt)
                ;
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key).compact();


    }
    public void verify(String authorization) throws Exception {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authorization);

        } catch (Exception e) {
            throw new Exception(String.valueOf(HttpStatus.UNAUTHORIZED));
        }
    }
}
