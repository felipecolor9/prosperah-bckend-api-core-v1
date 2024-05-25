package br.com.prosperah.api.appcore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailerConfig {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;


}
