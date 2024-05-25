package br.com.prosperah.api.appcore.utils;

import br.com.prosperah.api.appcore.exceptions.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.coyote.BadRequestException;

public class ValidationUtils {

    public static boolean isValidEmail(String email) throws BadRequestException {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidEmailException();
        }
        return true;
    }
}
