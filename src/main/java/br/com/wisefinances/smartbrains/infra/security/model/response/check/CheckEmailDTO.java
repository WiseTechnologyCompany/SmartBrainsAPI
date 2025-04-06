package br.com.wisefinances.smartbrains.infra.security.model.response.check;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckEmailDTO {

    @Email
    @NotNull
    private String email;

}