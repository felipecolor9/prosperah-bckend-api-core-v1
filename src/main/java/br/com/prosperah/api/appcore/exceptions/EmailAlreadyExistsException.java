package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException {

    public EmailAlreadyExistsException() {
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
