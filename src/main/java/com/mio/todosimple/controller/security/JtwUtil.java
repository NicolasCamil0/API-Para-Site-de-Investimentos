package com.mio.todosimple.controller.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;


import java.security.Key;
import java.util.Date;

public class JtwUtil {

    private String secretKey = "Cnys";
    private long validityseconds = 3600000;

    public String generateToken(String username){
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityseconds)) //
                .signWith(key, SignatureAlgorithm.HS256) //
                .compact(); //
    }

    public boolean validateToken (String token, String username){
        String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public String extractUsername (String token){
        return extractClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token){
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.parser()
                .setSigningKey(key) // Define a chave secreta usada para assinar o token
                .build()
                .parseClaimsJws(token) // Decodifica e valida o token JWT
                .getBody(); // Retorna o payload do token (claims)
    }

}