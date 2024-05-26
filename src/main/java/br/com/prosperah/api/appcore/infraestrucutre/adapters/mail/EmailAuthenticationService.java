package br.com.prosperah.api.appcore.infraestrucutre.adapters.mail;

import br.com.prosperah.api.appcore.config.MailerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(EmailAuthenticationService.class);

    @Autowired
    MailerConfig mailer;

    public void sendAuthenticationEmail(String emailAddress, String token) {
        Message message = initMessage(emailAddress, token);
        try {
            //TODO forma mais segura de passar credenciais (properties ou Authenticator)
            Transport.send(message, "*", "*");
            log.info("Email enviado para {}", emailAddress);
        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage());
        }
    }


    private Message initMessage(String emailAdress, String token) {
        var message = new MimeMessage(mailer.getDefaultSession().getSession());
        try {
            //TODO configuração de conta de email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdress));
            message.setSubject("Subject Here");
            message.setText("This is the message body." + token);
        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage());
        }
        return message;
    }
}

