package br.com.wisefinances.smartbrains.controller.password;

import br.com.wisefinances.smartbrains.model.dto.usuario.UserEmailDTO;
import br.com.wisefinances.smartbrains.service.password.CodigoVerificacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/codigo")
@Tag(name = "Código de Verificação")
public class CodigoVerificacaoController {

    @Autowired
    CodigoVerificacaoService codigoVerificacaoService;

    @PostMapping
    @Transactional
    public void saveCodigoVerificacao(@RequestBody @NotNull UserEmailDTO userEmailDTO) {
        codigoVerificacaoService.saveVerificationCode(userEmailDTO.getEmail());
    }
}