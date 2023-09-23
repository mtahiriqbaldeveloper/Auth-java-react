package com.brotech.Auth.JWT;

import com.brotech.Auth.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JWTUtils {

    @Value("${app.SECRET_KEY}")
    private String SECRET_KEY;
    @Value("${app.EXPIRATION_TIME}")
    private long  EXPIRATION_TIME = 120000L;
    public boolean validateToken(String token){
        try {

            Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
            return true;

        } catch (io.jsonwebtoken.MalformedJwtException e) {
            // Handle malformed JWT exception
            System.err.println("Malformed JWT: " + e.getMessage());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Handle expired JWT exception
            System.err.println("Expired JWT: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("Error parsing JWT: " + e.getMessage());
        }
        return false;
    }

    public String getUser(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }


}
