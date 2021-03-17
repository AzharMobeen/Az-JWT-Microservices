package com.az.jwt.example.util;

import com.az.jwt.example.exception.CustomRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {
    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public void validateTokenExpiration(String token) {
        if(extractExpiration(token).before(new Date()))
            throw  new CustomRuntimeException("JWT is expired",
                    "JWT is valid only for 30 min, please generate new JWT");
    }

    public String generateToken(UserDetails userDetails) {
        log.debug("generateToken method called {}", userDetails);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                //           converting milli seconds to  10 Hr     (1sec, 1min, 30min)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30 ))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}