package br.com.prosperah.api.appcore.infraestrucutre.adapters.mail;

import br.com.prosperah.api.appcore.config.MailerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(EmailAuthenticationService.class);

    @Autowired
    MailerConfig mailer;

    public void sendAuthenticationEmail(String emailAddress, String token) {

                //TODO Validar se precisa configurar uma conta de email

        try {

        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage());
        } finally {
            //TODO Validar se existe uma forma de fechar a conexao automaticamente e implementar
        }
    }
}
