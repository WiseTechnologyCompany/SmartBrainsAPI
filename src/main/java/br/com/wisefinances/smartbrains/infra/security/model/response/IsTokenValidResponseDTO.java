package br.com.wisefinances.smartbrains.infra.security.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsTokenValidResponseDTO {

    private Boolean isValid;

}