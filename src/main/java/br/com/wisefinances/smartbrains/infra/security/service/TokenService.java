package br.com.wisefinances.smartbrains.infra.security.service;

import br.com.wisefinances.smartbrains.infra.security.model.dto.AutenticacaoDTO;
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
public class TokenService {

    @Value("${smartbrains.api.security.jwt.secret}")
    private String secret;

    public String generateToken(AutenticacaoDTO autenticacaoDTO) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("SmartBrainsAPI")
                    .withSubject(autenticacaoDTO.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException ex) {
            throw new JWTCreationException("Ocorreu um erro ao tentar gerar o Token!", ex);
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("SmartBrainsAPI")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException ex) {
            throw new JWTVerificationException("O Token de acesso está inválido ou expirado!", ex);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}