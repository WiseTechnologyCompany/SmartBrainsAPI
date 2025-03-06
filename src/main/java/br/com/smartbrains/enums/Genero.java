package br.com.smartbrains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {

    MASCULINO(1, "MASCULINO"),
    FEMININO(2, "FEMININO"),
    OUTROS(3, "OUTROS");

    private final Integer id;
    private final String descricao;

}