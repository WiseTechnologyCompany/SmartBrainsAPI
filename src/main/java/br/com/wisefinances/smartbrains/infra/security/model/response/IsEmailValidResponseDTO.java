package br.com.wisefinances.smartbrains.infra.security.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IsEmailValidResponseDTO {

    private Boolean emailAlreadyExists;

}