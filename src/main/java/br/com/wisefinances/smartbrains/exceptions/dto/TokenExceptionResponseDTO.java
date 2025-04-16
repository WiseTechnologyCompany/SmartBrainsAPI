package br.com.wisefinances.smartbrains.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TokenExceptionResponseDTO {

    private int status;
    private String error;
    private String message;
    private String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public TokenExceptionResponseDTO(int status, String error, String message, String details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static final TokenExceptionResponseDTO invalidTokenResponseDTO = new TokenExceptionResponseDTO(
            403,
            "Forbidden",
            "Token Inválido!",
            "O Token de acesso está inválido! Por favor, autentique-se para obter um Token de acesso válido!"
    );

    public static final TokenExceptionResponseDTO requestWithoutTokenResponseDTO = new TokenExceptionResponseDTO(
            403,
            "Forbidden",
            "Token Inválido!",
            "O Token de acesso está ausente! Por favor, autentique-se para obter um Token de acesso válido!"
    );
}