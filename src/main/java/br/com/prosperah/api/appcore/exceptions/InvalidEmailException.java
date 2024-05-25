package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class InvalidEmailException extends BadRequestException {

    public InvalidEmailException() {

    }
    public InvalidEmailException(String message) {
        super(message);
    }
}
