package br.com.prosperah.api.appcore.exceptions;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.DatasourceService;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import javassist.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.prosperah.api.appcore.constants.Constants.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(DatasourceService.class);

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex) {

        if (ex instanceof InvalidEmailException) {
            log.info(EMAIL_INVALIDO, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 400] - BAD REQUEST: Email com formato inválido.", BAD_REQUEST.value());
        }

        if (ex instanceof EmailAlreadyExistsException) {
            log.info(EMAIL_EXISTENTE_TABELAS_CADASTRAIS, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 400] - BAD REQUEST: Email ja encontra-se em uso.", BAD_REQUEST.value());
        }

        if (ex instanceof UsernameAlreadyExistsException) {
            log.info(USUARIO_EXISTENTE_TABELAS_CADASTRAIS, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 400] - BAD REQUEST: Usuário ja encontra-se em uso.", BAD_REQUEST.value());
        }

        if (ex instanceof EmptyRequestBodyException) {
            log.info(REQUISICAO_BODY_VAZIO, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 400] - BAD REQUEST: Body vazio.", BAD_REQUEST.value());
        }

        if (ex instanceof EmptyFieldRequestBodyException) {
            log.info(REQUISICAO_CAMPO_VAZIO, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 400] - BAD REQUEST: Campo encontra-se vazio.", BAD_REQUEST.value());
        }

        if (ex instanceof WrongAuthCodeException) {
            log.info(CODIGO_AUTENTICACAO_INVALIDO, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 400] - BAD REQUEST: Código de autenticação errado ou com formato inválido.", BAD_REQUEST.value());
        }

        return new ResponseEntity<>(BAD_REQUEST.getReasonPhrase(), BAD_REQUEST.value());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {

        if (ex instanceof UserNotFoundException) {
            log.info(USUARIO_NAO_ENCONTRADO, ex.getLocalizedMessage());
            return new ResponseEntity<>("[STATUS 404] - NOT FOUND: Usuário não encontrado.", BAD_REQUEST.value());
        }
        return new ResponseEntity<>(NOT_FOUND.getReasonPhrase(), NOT_FOUND.value());

    }
}