package br.com.prosperah.api.appcore.exceptions;

import org.apache.coyote.BadRequestException;

public class WrongAuthCodeException extends BadRequestException {

    public WrongAuthCodeException() {
    }
    public WrongAuthCodeException(String message) {
        super(message);
    }

}
