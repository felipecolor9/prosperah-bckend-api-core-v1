package br.com.prosperah.api.appcore.exceptions;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.DatasourceService;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.prosperah.api.appcore.constants.Constants.*;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(DatasourceService.class);

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex) {

        if (ex instanceof InvalidEmailException) {
            log.info("[STATUS 400] - BAD REQUEST: EMAIL INVALIDO: {}", ex.getLocalizedMessage());
            return new ResponseEntity<>(EMAIL_INVALIDO, HttpStatus.BAD_REQUEST.value());
        }

        if (ex instanceof EmailAlreadyExistsException) {
            log.info("[STATUS 400] - BAD REQUEST: EMAIL JÁ CADASTRADO NAS BASES: {}", ex.getLocalizedMessage());
            return new ResponseEntity<>(EMAIL_EXISTENTE_TABELAS_CADASTRAIS, HttpStatus.BAD_REQUEST.value());
        }

        if (ex instanceof UsernameAlreadyExistsException) {
            log.info("[STATUS 400] - BAD REQUEST: USUÁRIO JÁ CADASTRADO NAS BASES: {}", ex.getLocalizedMessage());
            return new ResponseEntity<>(USUARIO_EXISTENTE_TABELAS_CADASTRAIS, HttpStatus.BAD_REQUEST.value());
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }
}