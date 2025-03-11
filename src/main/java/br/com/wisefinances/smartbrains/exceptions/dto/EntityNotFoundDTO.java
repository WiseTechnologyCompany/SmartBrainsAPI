package br.com.wisefinances.smartbrains.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class EntityNotFoundDTO {

    private int status;
    private String error;
    private String message;
    private String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public EntityNotFoundDTO(int status, String error, String message, String details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static final EntityNotFoundDTO entityNotFoundDTO = new EntityNotFoundDTO(
            404,
            "Not Found",
            "Registro não encontrado!",
            "Não foi possível localizar um registro com o ID informado!"
    );
}