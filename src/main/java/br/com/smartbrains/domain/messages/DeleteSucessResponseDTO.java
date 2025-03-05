package br.com.smartbrains.domain.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class DeleteSucessResponseDTO {

    private Integer status;
    private String message;
    private String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timestamp;

    public DeleteSucessResponseDTO(Integer status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static DeleteSucessResponseDTO deleteSucessResponseDTO = new DeleteSucessResponseDTO(
            200,
            "Sucesso",
            "O Registro informado foi deletado com sucesso!"
    );
}