package br.com.wisefinances.smartbrains.model.modify.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "usuarios", schema = "public")
public class ModifyUsuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 100)
    @Column(name = "sobrenome", length = 100, nullable = false)
    private String sobrenome;

    @Email
    @NotNull
    @Size(max = 100)
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @NotNull
    @Size(max = 14)
    @Column(name = "cpf", length = 14, unique = true, nullable = false)
    private String cpf;

    @NotNull
    @Size(max = 100)
    @Column(name = "profissao", length = 100, nullable = false)
    private String profissao;

    @NotNull
    @Size(max = 100)
    @Column(name = "empresa", length = 100, nullable = false)
    private String empresa;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull
    @Size(max = 20)
    @Column(name = "telefone", length = 20, nullable = false)
    private String telefone;

    @NotNull
    @Column(name = "id_genero", nullable = false)
    private Integer genero;

    @NotNull
    @Column(name = "id_estadocivil", nullable = false)
    private Integer estadoCivil;

}