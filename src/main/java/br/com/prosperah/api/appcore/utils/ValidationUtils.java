package br.com.prosperah.api.appcore.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidationUtils {

    public static boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
