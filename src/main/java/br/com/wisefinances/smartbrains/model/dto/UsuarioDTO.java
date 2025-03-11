package br.com.wisefinances.smartbrains.model.dto;

import br.com.wisefinances.smartbrains.model.entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UsuarioDTO {

    private Integer id;

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
    private String genero;

    @NotNull
    private String estadoCivil;

    private String situacaoCadastro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public UsuarioDTO(Usuarios usuarios) {
        this.id = usuarios.getId();
        this.nome = usuarios.getNome();
        this.sobrenome = usuarios.getSobrenome();
        this.email = usuarios.getEmail();
        this.senha = usuarios.getSenha();
        this.cpf = usuarios.getCpf();
        this.profissao = usuarios.getProfissao();
        this.empresa = usuarios.getEmpresa();
        this.dataNascimento = usuarios.getDataNascimento();
        this.telefone = usuarios.getTelefone();
        this.genero = usuarios.getGenero().getDescricao();
        this.estadoCivil = usuarios.getEstadoCivil().getDescricao();
        this.situacaoCadastro = usuarios.getSituacaoCadastro().getDescricao();
        this.dataCadastro = usuarios.getDataCadastro();
    }
}