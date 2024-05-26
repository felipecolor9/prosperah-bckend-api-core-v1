package br.com.prosperah.api.appcore.infraestrucutre.adapters.mail;

import br.com.prosperah.api.appcore.config.MailerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import static br.com.prosperah.api.appcore.constants.Constants.EmailConstants.*;
import static javax.mail.Message.RecipientType.TO;
import static javax.mail.Transport.*;
import static javax.mail.internet.InternetAddress.parse;

@Service
public class EmailAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(EmailAuthenticationService.class);

    @Autowired
    MailerConfig mailer;

    public void sendAuthenticationEmail(String emailAddress, String token) {
        Message message = initMessage(emailAddress, token);
        try {
            send(message);
            System.out.println(token); //TODO Para testes - Remover futuramente
            log.info(EMAIL_ENVIADO, emailAddress);
        } catch (Exception ex) {
            log.error(EMAIL_ERRO, ex.getMessage());
        }
    }


    private Message initMessage(String emailAddress, String token) {
        var message = new MimeMessage(mailer.getDefaultMessage().getSession());
        try {
            message.setRecipients(TO, parse(emailAddress));
            message.setSubject("Falta só mais um passo para voce concluir o cadastro!");
            message.setText("Olá! utilize o código para se autenticar no sistema: " + token);
        } catch (Exception ex) {
            log.error(EMAIL_ERRO, ex.getMessage());
        }
        return message;
    }
}

