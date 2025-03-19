package com.mio.todosimple.controller.security;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.repositories.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;

import java.util.Date;
import java.util.Optional;

public class JtwUtil {

    @Autowired
    private Usuario usuario;

    @Autowired
    private UserRepository userRepository;

    private final String secretKey = "Cnys";
    private final long validitySeconds = 3600000; // 1 hora

    public String generateToken(String username) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Optional<Usuario> usuarioOptional = userRepository.findByUsername(username);

        try {
            return Jwts.builder().subject(username) //
                    .claim("id", usuario.getId()) //
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + validitySeconds))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expirado", e);
        } catch (JwtException e) {
            throw new RuntimeException("Token inválido", e);
        }
    }

    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return (usuario.getUsername().equals(tokenUsername) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseSignedClaims(token)
                .getPayload(); //
    }

    public Long obterIdUsuario(String token) {
        Claims claims = extractClaims(token);
        return Long.parseLong(claims.get("id", String.class));
    }

}