package br.com.wisefinances.smartbrains.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ForbiddenExceptionResonseDTO {

    private int statusCode;
    private String error;
    private String message;
    private String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public ForbiddenExceptionResonseDTO(int statusCode, String error, String message, String details) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static final ForbiddenExceptionResonseDTO forbiddenResponseDTO = new ForbiddenExceptionResonseDTO(
            403,
            "Forbidden",
            "Acesso Negado!",
            "Seu usuáio não tem permissão para acessar este recurso! Verifique suas credenciais!"
    );
}