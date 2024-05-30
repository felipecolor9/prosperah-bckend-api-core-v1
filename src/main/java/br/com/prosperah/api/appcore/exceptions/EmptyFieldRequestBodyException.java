package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class EmptyFieldRequestBodyException extends BadRequestException {

    public EmptyFieldRequestBodyException() {
    }

    public EmptyFieldRequestBodyException(String message) {
        super(message);
    }
}
