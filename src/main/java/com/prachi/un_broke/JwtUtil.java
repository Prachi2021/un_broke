package com.prachi.un_broke;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parser;

@Component
public class JwtUtil {

    @Value("")
    private String secretKey = "";

    @Value("${jwt.expiration}")
    private int expiration = 604800;


    public String generateToken(String username) {
        return builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * expiration)) // Example expiration
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey() {
        // Converts your secret key string to a Key object that JJwt can use
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return parser()  // Use parserBuilder() for newer versions
                .setSigningKey(getSigningKey()) // Supply the key as a Key object
                .build() // Build the parser
                .parseClaimsJws(token) // Parse the token
                .getBody(); // Extract the claims
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public Boolean validateToken(String token, String username) {
        final String usernameInToken = extractUsername(token);
        return (usernameInToken.equals(username) && !isTokenExpired(token));
    }
}

