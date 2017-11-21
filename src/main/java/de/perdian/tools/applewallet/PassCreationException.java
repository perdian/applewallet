package de.perdian.tools.applewallet;

public class PassCreationException extends RuntimeException {

    static final long serialVersionUID = 1L;

    PassCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    PassCreationException(String message) {
        super(message);
    }

}
