package com.bookstoreapi.bookstoreapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtUtils {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bookstoreapi.jwt.secret}")
    private String jwtSecret;
    @Value("${bookstoreapi.jwt.expireDuration}")
    private int jwtExpiration;

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJwt(token).getBody().getSubject();
    }


    public String generateToken (Authentication authentication) {
        UserDetailsImplementation userPrincipal = (UserDetailsImplementation) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
