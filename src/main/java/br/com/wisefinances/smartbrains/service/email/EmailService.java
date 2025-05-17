package br.com.wisefinances.smartbrains.service.email;

import br.com.wisefinances.smartbrains.repository.usuario.UsuarioRepository;
import br.com.wisefinances.smartbrains.util.DateUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Value("${EMAIL_USERNAME}")
    private String from;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UsuarioRepository usuarioRepository;

    public void sendEmail(String pUserEmail, String pVerificationCode) {
        log.info("Enviando Código de verificação para o e-mail: {} - {}", pUserEmail, DateUtils.getCurrentDateTimeFormatted());

        try {
            String userName = getUserName(pUserEmail);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(pUserEmail);
            helper.setSubject("Código de Verificação");

            String htmlContent = "<html>" +
                    "<body style=\"font-family: Arial, sans-serif; text-align: center; padding: 40px; background-color: #f8f9fa;\">" +
                    "<div style=\"max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);\">" +
                    "<h2 style=\"color: #2c3e50;\">Olá, " + userName + " </h2>" +
                    "<p style=\"font-size: 16px; color: #333;\">Segue abaixo o seu <strong>Código de Verificação</strong>:</p>" +
                    "<br/>" +
                    "<div style=\"margin: 20px 0;\">" +
                    "<span style=\"display: inline-block; background-color: #2980b9; color: #ffffff; padding: 15px 30px; " +
                    "font-size: 22px; border-radius: 6px; font-weight: bold; letter-spacing: 2px;\">" +
                    pVerificationCode +
                    "</span>" +
                    "</div>" +
                    "<br/>" +
                    "<p style=\"font-size: 15px; color: #333;\">Utilize este código para redefinir sua senha.</p>" +
                    "<br/>" +
                    "<p style=\"color: #7f8c8d; font-size: 14px;\">Caso você não tenha solicitado este código,<br/>por favor, ignore esta mensagem.</p>" +
                    "<br/>" +
                    "<p style=\"font-size: 15px; color: #333;\">Atenciosamente,<br/><strong>Wise Technology Company</strong></p>" +
                    "</div>" +
                    "</body></html>";

            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um erro ao enviar o e-mail de verificação", ex.getCause());
        }
    }

    private String getUserName(String pEmail) {
        return usuarioRepository.findUserNameByEmail(pEmail);
    }
}