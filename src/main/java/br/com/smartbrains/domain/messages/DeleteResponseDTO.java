package br.com.smartbrains.domain.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class DeleteResponseDTO {

    private Integer status;
    private String message;
    private String details;
    private String exception;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public DeleteResponseDTO(Integer status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO(
            200,
            "Sucesso",
            "O Registro informado foi deletado com sucesso!"
    );
}