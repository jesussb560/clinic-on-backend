package com.app.clinicon.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.app.clinicon.configuration.TokenPropertiesConfig;
import com.app.clinicon.user.UserPrincipal;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.gson.io.GsonDeserializer;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider implements Serializable {

    private final TokenPropertiesConfig tokenPropertiesConfig;

    public String generateToken(Authentication authentication) {

        Date now = new Date();
        Gson gson = new Gson();

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Key secretKey = Keys.hmacShaKeyFor(tokenPropertiesConfig.getSecret().getBytes());
        Date expiryDate = new Date(now.getTime() + tokenPropertiesConfig.getExpirationInMs());

        return Jwts.builder()
                .serializeToJsonWith(new GsonSerializer<>(gson))
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
                
    }

    public Long getUserIdFromJWT(String token) {

        Gson gson = new Gson();

        Claims claims = Jwts.parserBuilder()
                .deserializeJsonWith(new GsonDeserializer<>(gson))
                .setSigningKey(tokenPropertiesConfig.getSecret().getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());

    }

    public boolean validateToken(String authToken) {

        try {

            Gson gson = new Gson();

            Jwts.parserBuilder()
            .deserializeJsonWith(new GsonDeserializer<>(gson))
            .setSigningKey(tokenPropertiesConfig.getSecret().getBytes())
            .build()
            .parseClaimsJws(authToken)
            .getBody();

            return true;

        } catch (IncorrectClaimException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }

        return false;

    }

}
