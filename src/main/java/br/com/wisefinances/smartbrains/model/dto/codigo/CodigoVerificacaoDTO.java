package br.com.wisefinances.smartbrains.model.dto.codigo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodigoVerificacaoDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String codigo;

}