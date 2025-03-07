package br.com.smartbrains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoCivil {

    SOLTEIRO(1, "SOLTEIRO"),
    UNIAO_ESTAVEL(2, "UNIAO ESTAVEL"),
    CASADO(3, "CASADO"),
    DIVORCIADO(4, "DIVORCIADO"),
    VIUVO(5, "VIUVO");

    private final Integer id;
    private final String descricao;
}