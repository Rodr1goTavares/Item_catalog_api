package br.com.rodr1gotavares.item_catalog.api.security;

import br.com.rodr1gotavares.item_catalog.entity.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("");
            return JWT.create()
                    .withIssuer("item_catalog")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);
        }
        catch(JWTCreationException e) {
            throw new RuntimeException("Error to generate JWT token", e);
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusSeconds(240).toInstant(ZoneOffset.of("-03:00"));
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("item_catalog")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException e) {
            return "";
        }
    }
}
