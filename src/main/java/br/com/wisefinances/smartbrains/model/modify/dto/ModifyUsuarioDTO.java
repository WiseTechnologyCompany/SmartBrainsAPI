package br.com.wisefinances.smartbrains.model.modify.dto;

import br.com.wisefinances.smartbrains.model.modify.entity.ModifyUsuario;
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
public class ModifyUsuarioDTO {

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

    public ModifyUsuarioDTO(ModifyUsuario modifyUsuario) {
        this.nome = modifyUsuario.getNome();
        this.sobrenome = modifyUsuario.getSobrenome();
        this.email = modifyUsuario.getEmail();
        this.senha = modifyUsuario.getSenha();
        this.cpf = modifyUsuario.getCpf();
        this.profissao = modifyUsuario.getProfissao();
        this.empresa = modifyUsuario.getEmpresa();
        this.dataNascimento = modifyUsuario.getDataNascimento();
        this.telefone = modifyUsuario.getTelefone();
        this.genero = modifyUsuario.getGenero();
        this.estadoCivil = modifyUsuario.getEstadoCivil();
    }
}