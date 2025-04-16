package br.com.wisefinances.smartbrains.model.entity.situacaocadastro;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "situacao_cadastro", schema = "public")
public class SituacaoCadastro {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Column(name = "descricao", length = 50, unique = true, nullable = false)
    private String descricao;

}