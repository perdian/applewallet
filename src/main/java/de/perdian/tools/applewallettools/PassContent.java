package de.perdian.tools.applewallettools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Collector object that stores all the content to be written into the target Apple Wallet pass.
 *
 * @author Christian Robert
 */

class PassContent {

    private static final Logger log = LoggerFactory.getLogger(PassContent.class);
    private Map<String, byte[]> content = null;

    PassContent() {
        this.setContent(new LinkedHashMap<>());
    }

    /**
     * Adds a new file to the pass
     *
     * @param fileName
     *      the name of the file within the pass archive
     * @param bytes
     *      the content to be added
     */
    void add(String fileName, byte[] bytes) {
        log.debug("Adding entry: {} ({} bytes)", fileName, bytes.length);
        this.getContent().put(fileName, bytes);
    }

    /**
     * Creates the content of the pass
     *
     * @return
     *      the bytes representing the content of the actual pass (which basically is a zipped
     *      file including a signature).
     */
    byte[] toZipFileContent(PassSigner passSigner) throws IOException {

        Map<String, byte[]> sourceFiles = this.getContent();
        Map<String, byte[]> outputFiles = new LinkedHashMap<>(sourceFiles);

        // Create the manifest listing all the files stored inside the zip file
        byte[] manifestBytes = PassContent.createManifestBytes(sourceFiles);
        byte[] signatureBytes = passSigner.createSignature(manifestBytes);
        outputFiles.put("manifest.json", manifestBytes);
        outputFiles.put("signature", signatureBytes);

        // Append everything as ZIP
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteOutputStream)) {
            for (Map.Entry<String, byte[]> outputFileEntry : outputFiles.entrySet()) {
                zipOutputStream.putNextEntry(new ZipEntry(outputFileEntry.getKey()));
                zipOutputStream.write(outputFileEntry.getValue());
            }
        }
        return byteOutputStream.toByteArray();

    }

    private static byte[] createManifestBytes(Map<String, byte[]> sourceFiles) throws IOException {
        JsonObject manifestObject = new JsonObject();
        for (Map.Entry<String, byte[]> sourceFile : sourceFiles.entrySet()) {
            manifestObject.addProperty(sourceFile.getKey(), PassContent.createSha1HashToString(sourceFile.getValue()));
        }
        return new GsonBuilder().setPrettyPrinting().create().toJson(manifestObject).getBytes("UTF-8");
    }

    private static String createSha1HashToString(byte[] content) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(content);
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("SHA-1 algorithm cannot be found!", e);
        }
    }

    private Map<String, byte[]> getContent() {
        return this.content;
    }
    private void setContent(Map<String, byte[]> content) {
        this.content = content;
    }

}
