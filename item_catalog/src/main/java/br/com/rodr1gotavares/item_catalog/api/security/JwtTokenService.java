package br.com.rodr1gotavares.item_catalog.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    public String generateToken(UserDetails user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer("item_catalog")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);
        }
        catch(JWTCreationException e) {
            throw new RuntimeException("Error to generate JWT token:\n", e);
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusSeconds(240).toInstant(ZoneOffset.of("-03:00"));
    }

    public String verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("item_catalog")
                .build()
                .verify(token)
                .getSubject();
    }
}
