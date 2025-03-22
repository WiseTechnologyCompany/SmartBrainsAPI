package br.com.wisefinances.smartbrains.exceptions.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
public class BadRequestExceptionResponseDTO {

    private String campo;
    private String mensagem;

    public BadRequestExceptionResponseDTO(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }
}