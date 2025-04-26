package br.com.wisefinances.smartbrains.model.entity.movimentacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "movimentacao", schema = "public")
public class CreateMovimentacao {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer usuario;

    @NotNull
    @Column(name = "id_tipomovimentacao", nullable = false)
    private Integer tipoMovimentacao;

    @NotNull
    @Column(name = "id_tipocategoria", nullable = false)
    private Integer tipoCategoria;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @NotNull
    @Column(name = "valor", precision = 11, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "data_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

}