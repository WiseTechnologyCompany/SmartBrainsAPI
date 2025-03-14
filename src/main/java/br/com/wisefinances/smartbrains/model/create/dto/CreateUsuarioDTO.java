package br.com.wisefinances.smartbrains.model.create.dto;

import br.com.wisefinances.smartbrains.model.create.entity.CreateUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private LocalDate dataNascimento;

    @NotNull
    @Size(max = 20)
    private String telefone;

    @NotNull
    private Integer genero;

    @NotNull
    private Integer estadoCivil;

    public CreateUsuarioDTO(CreateUsuario createUsuario) {
        this.nome = createUsuario.getNome();
        this.sobrenome = createUsuario.getSobrenome();
        this.email = createUsuario.getEmail();
        this.senha = createUsuario.getSenha();
        this.cpf = createUsuario.getCpf();
        this.profissao = createUsuario.getProfissao();
        this.empresa = createUsuario.getEmpresa();
        this.dataNascimento = createUsuario.getDataNascimento();
        this.telefone = createUsuario.getTelefone();
        this.genero = createUsuario.getGenero();
        this.estadoCivil = createUsuario.getEstadoCivil();
    }
}