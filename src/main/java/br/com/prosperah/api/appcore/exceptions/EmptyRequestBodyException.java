package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class EmptyRequestBodyException extends BadRequestException {

    public EmptyRequestBodyException() {
    }

    public EmptyRequestBodyException(String message) {
        super(message);
    }
}
