package br.com.wisefinances.smartbrains.model.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInfoRequestDTO {

    @Email
    @NotNull
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

}