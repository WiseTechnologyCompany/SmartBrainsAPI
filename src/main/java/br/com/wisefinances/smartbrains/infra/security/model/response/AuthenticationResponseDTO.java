package br.com.wisefinances.smartbrains.infra.security.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuthenticationResponseDTO {

    private int status;
    private String message;
    private String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public AuthenticationResponseDTO(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static final AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO(
            200,
            "Ok!",
            "O Usuário foi criado com sucesso!"
    );
}