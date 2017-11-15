package de.perdian.tools.applewallettools;

/**
 * Information about a {@link Pass} that has been signed and is ready to be downloaded
 * into the Apple Wallet app.
 *
 * @author Christian Robert
 */

public class SignedPass {

    private byte[] bytes = null;

    public byte[] getBytes() {
        return this.bytes;
    }
    void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
