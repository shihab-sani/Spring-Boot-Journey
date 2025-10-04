package spring.secondproject.Services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.jar.JarException;

@Service
public class JwtServices {
    @Value( "${spring.jwt.secret}")
    private String secret;

    public String generateJwtToken(String email) {
        final long tokenExpiration = 86400; //1 day
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parseSignedClaims(authToken)
                    .getPayload();
            return claims.getExpiration().after(new Date());
        } catch (JwtException ex) {
            return false;
        }
    }
}
