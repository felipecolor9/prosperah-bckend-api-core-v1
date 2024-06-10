package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class InvalidOperationException extends BadRequestException {
    public InvalidOperationException() {

    }
    public InvalidOperationException(String message) {
        super(message);
    }
}
