package br.com.prosperah.api.appcore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.*;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("br.com.prosperah.api.appcore"))
                .paths(PathSelectors.regex("/*.*"))
                .build().apiInfo(apiInfoMethod());
    }

    private ApiInfo apiInfoMethod() {

        return new ApiInfoBuilder()
                .title("API CORE - PROSPERAH")
                .description("Operações básicas para o sistema PROSPERAH")
                .contact(new Contact("Felipe Marues", "https://www.github.com/felipecolor9", "felipe.m199@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0")
                .build();
    }
}