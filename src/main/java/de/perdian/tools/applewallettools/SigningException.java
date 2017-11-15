package de.perdian.tools.applewallettools;

/**
 * Signalizes that an error has occured while trying to create an Apple Wallet pass.
 *
 * @author Christian Robert
 */

public class SigningException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public SigningException(String message, Throwable cause) {
        super(message, cause);
    }

    public SigningException(String message) {
        super(message);
    }

}
