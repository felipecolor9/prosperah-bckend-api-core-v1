package br.com.prosperah.api.appcore.utils.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public class SessionManager {


    public Cookie getSessionCookie() {
        var cookie = new Cookie("sessionHash", generateSessionHash());
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        return cookie;
    }

    /**
     * Generates a session hash by creating a random byte array and converting it to a hexadecimal representation.
     *
     * @return The session hash generated as a hexadecimal string.
     */
    private String generateSessionHash() {
        var random = new SecureRandom();
        byte[] sessionBytes = new byte[32]; // Adjust the byte array size as needed
        random.nextBytes(sessionBytes);
        var sessionNumber = new BigInteger(1, sessionBytes);

        return sessionNumber.toString(16); // Convert to hexadecimal representation
    }

}
