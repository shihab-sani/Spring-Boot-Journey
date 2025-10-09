package spring.secondproject.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.secondproject.Config.JwtConfig;
import spring.secondproject.Entities.Role;
import spring.secondproject.Entities.User;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtServices {
    private final JwtConfig jwtConfig;


    public String generateAccessToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private String generateToken(User user, long tokenExpiration) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            var claims = getClaims(authToken);
            return claims.getExpiration().after(new Date());
        } catch (JwtException ex) {
            return false;
        }
    }

    private Claims getClaims(String authToken) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build().parseSignedClaims(authToken)
                .getPayload();
    }

    public Long getUserIdFromJwtToken(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }

    public Role getRoleFromJwtToken(String token) {
        return Role.valueOf(getClaims(token).get("role", String.class));
    }
}
