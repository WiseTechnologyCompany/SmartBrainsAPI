package br.com.wisefinances.smartbrains.infra.security.service;

import br.com.wisefinances.smartbrains.infra.security.model.dto.AutenticacaoDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${smartbrains.api.security.jwt.secret}")
    private String secret;

    private static final String ISSUER = "SmartBrainsAPI";

    public String generateToken(AutenticacaoDTO autenticacaoDTO) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(autenticacaoDTO.getEmail())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException ex) {
            throw new JWTVerificationException("O Token de acesso está inválido ou expirado!", ex);
        }
    }

    public Boolean isTokenValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

            return true;
        }
        catch (JWTVerificationException ex) {
            return false;
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}