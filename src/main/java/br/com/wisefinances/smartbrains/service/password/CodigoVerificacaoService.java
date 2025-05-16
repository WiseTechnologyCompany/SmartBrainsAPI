package br.com.wisefinances.smartbrains.service.password;

import br.com.wisefinances.smartbrains.model.dto.password.CodigoVerificacaoDTO;
import br.com.wisefinances.smartbrains.model.entity.password.CodigoVerificacao;
import br.com.wisefinances.smartbrains.repository.password.CodigoVerificacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class CodigoVerificacaoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CodigoVerificacaoRepository codigoVerificacaoRepository;

    public void saveVerificationCode(String pEmail) {
        var verificationCode = createVerificationCode(pEmail);
        var verificationCodeToSave = modelMapper.map(verificationCode, CodigoVerificacao.class);
        codigoVerificacaoRepository.save(verificationCodeToSave);
    }

    private CodigoVerificacaoDTO createVerificationCode(String pEmail) {
        CodigoVerificacaoDTO verificationCodeDTO = new CodigoVerificacaoDTO();

        int verificationCode = generateVerificationCode();
        String formattedverificationCode = formatVerificationCode(verificationCode);

        verificationCodeDTO.setEmail(pEmail);
        verificationCodeDTO.setCodigo(formattedverificationCode);

        return verificationCodeDTO;
    }

    private String formatVerificationCode(int codigoVerificacao) {
        String code = String.valueOf(codigoVerificacao);
        return code.substring(0, 4) + "-" + code.substring(4);
    }

    private int generateVerificationCode() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }
}