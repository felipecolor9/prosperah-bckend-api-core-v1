package br.com.prosperah.api.appcore.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository")
@EntityScan(basePackages = "br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model")
public class JpaConfig {
}