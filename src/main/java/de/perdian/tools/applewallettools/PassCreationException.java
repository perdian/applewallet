package de.perdian.tools.applewallettools;

public class PassCreationException extends RuntimeException {

    static final long serialVersionUID = 1L;

    PassCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    PassCreationException(String message) {
        super(message);
    }

}
