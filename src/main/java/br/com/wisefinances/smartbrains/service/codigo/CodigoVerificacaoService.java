package br.com.wisefinances.smartbrains.service.codigo;

import br.com.wisefinances.smartbrains.model.dto.codigo.CodigoVerificacaoDTO;
import br.com.wisefinances.smartbrains.model.entity.codigo.CodigoVerificacao;
import br.com.wisefinances.smartbrains.repository.codigo.CodigoVerificacaoRepository;
import br.com.wisefinances.smartbrains.service.email.EmailService;
import br.com.wisefinances.smartbrains.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Random;

@Slf4j
@Service
public class CodigoVerificacaoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    CodigoVerificacaoRepository codigoVerificacaoRepository;

    @Async
    @Scheduled(cron = "0 0 4 ? * SUN")
    public void clearVerificationCodesTable() {
        LocalDate today = LocalDate.now();
        LocalDate firstSunday = today.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));

        if (today.equals(firstSunday)) {
            log.info("Deleting Old Verification Codes... - {}", DateUtils.getCurrentDateTimeFormatted());
            codigoVerificacaoRepository.deleteAll();
            log.info("Old Verification Codes were successfully deleted - {}", DateUtils.getCurrentDateTimeFormatted());
        }
    }

    public void saveVerificationCode(String pEmail) {
        var verificationCode = createVerificationCode(pEmail);
        var verificationCodeToSave = modelMapper.map(verificationCode, CodigoVerificacao.class);
        codigoVerificacaoRepository.save(verificationCodeToSave);
        emailService.sendEmail(pEmail, verificationCode.getCodigo());
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