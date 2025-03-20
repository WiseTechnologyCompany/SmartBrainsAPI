package br.com.wisefinances.smartbrains.model.create.dto;

import br.com.wisefinances.smartbrains.model.create.entity.CreateCofre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCofreDTO {

    private Integer usuario;

    private Integer tipoMovimentacao;

    private BigDecimal valor;

    private String observacao;

    public CreateCofreDTO(CreateCofre createCofre) {
        this.usuario = createCofre.getUsuario();
        this.tipoMovimentacao = createCofre.getTipoMovimentacao();
        this.valor = createCofre.getValor();
        this.observacao = createCofre.getObservacao();
    }
}