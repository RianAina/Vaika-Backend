package com.vaika.backend.service;

import com.vaika.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtService {
    public final String ENCRIPTION_KEY = "b11f245de47fd67f0215b7e30f7bf4337c39e76fd87148305397344aa8a4c92f";
    private UserService userService;
    public Map<String,String> generate(String email){
        User user = (User) this.userService.loadUserByUsername(email);
        return this.generateJwt(user);
    }
    /*claims = les informations que l'on veut transmettre pour les utilisateur*/
    private Map<String, String> generateJwt(User user) {
        final Map<String, String> claims = Map.of(
                "nom", user.getName(),
                "email", user.getEmail()
        );
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime+ 30*60*1000;

        final String token = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("token",token);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);;
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    private <T> T getClaim(String token, Function<Claims, T> function){
        Claims claims = getAllClaims(token);
            return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
}


