package br.com.smartbrains.model.dto.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsuarioDTO {

    @NotNull
    @Size(max = 100)
    private String nome;

    @NotNull
    @Size(max = 100)
    private String sobrenome;

    @Email
    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    private String senha;

    @NotNull
    @Size(max = 14)
    private String cpf;

    @NotNull
    @Size(max = 100)
    private String profissao;

    @NotNull
    @Size(max = 100)
    private String empresa;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotNull
    @Size(max = 20)
    private String telefone;

    @NotNull
    private Integer genero;

    @NotNull
    private Integer estadoCivil;

}