package com.srap.Shree.Radhe.Agro.Foods.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtils {
    private final String SECRET = "w8nXKq4bT0sFYRzM7hHj1Nud3aPVoC9xv2U6mLfRkpJg5iObyE0WcQlAZtDSrG8M";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *2))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        Claims body = Jwts
                .parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }
    public boolean verifyToken(String token, UserDetails userDetails){
        Claims body = Jwts
                .parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (!body.getExpiration().before(new Date()) && userDetails.getUsername().equals(body.getSubject()));
    }
}
