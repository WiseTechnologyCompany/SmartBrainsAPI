package br.com.wisefinances.smartbrains.model.entity.cofre;

import br.com.wisefinances.smartbrains.model.entity.tipomovimentacao.TipoMovimentacao;
import br.com.wisefinances.smartbrains.model.entity.usuario.Usuarios;
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
@Table(name = "cofre", schema = "public")
public class Cofre {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipomovimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    @Column(name = "valor", precision = 11, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "data_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

}