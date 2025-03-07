package br.com.smartbrains.domain.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class MessagesResponseDTO {

    private Integer status;
    private String message;
    private String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public MessagesResponseDTO(Integer status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static final MessagesResponseDTO createSucessResponseDTO = new MessagesResponseDTO(
            201,
            "Sucesso",
            "O Registro foi salvo com sucesso!"
    );

    public static final MessagesResponseDTO deleteSucessResponseDTO = new MessagesResponseDTO(
            200,
            "Sucesso",
            "O Registro solicitado foi deletado com sucesso!"
    );
}