package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class UsernameAlreadyExistsException extends BadRequestException {

    public UsernameAlreadyExistsException() {
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
