package com.example.hackathon6backend.global.security.jwt;

import com.example.hackathon6backend.domain.user.entity.role.Role;
import com.example.hackathon6backend.global.exception.ExpiredJwtException;
import com.example.hackathon6backend.global.exception.InvalidJwtException;
import com.example.hackathon6backend.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final SecretKeySpec secretKeySpec;

    public JwtTokenProvider(JwtProperties jwtProperties, AuthDetailsService authDetailsService) {
        this.jwtProperties = jwtProperties;
        this.authDetailsService = authDetailsService;
        this.secretKeySpec = new SecretKeySpec(jwtProperties.getSecretKey().getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String accountId, String type, Long exp, Role role) {
        return Jwts.builder()
            .signWith(secretKeySpec)
            .setSubject(accountId)
            .setHeaderParam("type", type)
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
            .compact();
    }

    public String generateAccessToken(String accountId, Role role) {
        return generateToken(accountId, "access", jwtProperties.getAccessExp(), role);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.replace(jwtProperties.getPrefix(), "").trim();
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearerToken);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secretKeySpec)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            if (e instanceof io.jsonwebtoken.ExpiredJwtException) {
                throw ExpiredJwtException.EXCEPTION;
            } else {
                throw InvalidJwtException.EXCEPTION;
            }
        }
    }

    private String getTokenSubject(String token) {
        return getClaims(token).getSubject();
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}