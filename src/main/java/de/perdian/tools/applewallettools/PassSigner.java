package de.perdian.tools.applewallettools;

import java.io.IOException;

public interface PassSigner {

    /**
     * Creates the signature of the given content
     *
     * @param content
     *      the content to be signed
     * @return
     *      the signed content
     * @throws IOException
     *      thrown if the signing process fails
     */
    byte[] createSignature(byte[] content) throws IOException;

}
