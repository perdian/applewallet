package de.perdian.tools.applewallettools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.activation.DataSource;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

/**
 * {@link PassSigner} implementation using the data stored inside a key store
 *
 * @author Christian Robert
 */

public class KeyStorePassSigner implements PassSigner {

    private DataSource keyStore = null;
    private String keyStoreType = "PKCS12";
    private char[] keyStorePassword = null;
    private String keyName = null;
    private DataSource cert = null;
    private DataSource ca = null;

    @Override
    public byte[] createSignature(byte[] content) throws IOException {
        PrivateKey privateKey = this.openKey();
        if (privateKey == null) {
            throw new IOException("Cannot find private key in KeyStore for name: " + this.getKeyName());
        } else {
            X509CertificateHolder certHolder = this.openCertificate(this.getCert());
            X509CertificateHolder caHolder = this.openCertificate(this.getCa());
            return this.createSignatureInternal(caHolder, certHolder, privateKey, content);
        }
    }

    private byte[] createSignatureInternal(X509CertificateHolder caHolder, X509CertificateHolder certHolder, PrivateKey privateKey, byte[] data) throws IOException {
        try {

            String bouncyCastleProvider = "BC";
            Security.addProvider(new BouncyCastleProvider());

            JcaCertStore certStore = new JcaCertStore(Arrays.asList(certHolder, caHolder));
            JcaContentSignerBuilder jcaContentSignerBuilder = new JcaContentSignerBuilder("SHA1withRSA");
            jcaContentSignerBuilder.setProvider(bouncyCastleProvider);
            ContentSigner contentSignerSigner = jcaContentSignerBuilder.build(privateKey);

            JcaDigestCalculatorProviderBuilder jcaDigestCalculatorProviderBuilder = new JcaDigestCalculatorProviderBuilder();
            jcaDigestCalculatorProviderBuilder.setProvider(bouncyCastleProvider);
            DigestCalculatorProvider digestCalculatorProvider = jcaDigestCalculatorProviderBuilder.build();
            JcaSignerInfoGeneratorBuilder jcaSignerInfoGeneratorBuilder = new JcaSignerInfoGeneratorBuilder(digestCalculatorProvider);
            SignerInfoGenerator signerInfoGenerator = jcaSignerInfoGeneratorBuilder.build(contentSignerSigner, certHolder);

            CMSSignedDataGenerator cmsSignedDataGenerator = new CMSSignedDataGenerator();
            cmsSignedDataGenerator.addSignerInfoGenerator(signerInfoGenerator);
            cmsSignedDataGenerator.addCertificates(certStore);

            CMSTypedData cmsTypedData = new CMSProcessableByteArray(data);
            CMSSignedData cmsSignedData = cmsSignedDataGenerator.generate(cmsTypedData, false);
            return cmsSignedData.getEncoded();

        } catch (Exception e) {
            throw new IOException("Cannot sign manifest", e);
        }
    }

    private PrivateKey openKey() throws IOException {
        KeyStore keyStore = this.openKeyStore();
        try {
            return (PrivateKey)keyStore.getKey(this.getKeyName(), this.getKeyStorePassword());
        } catch (Exception e) {
            throw new IOException("Cannot open key from KeyStore for name: " + this.getKeyName(), e);
        }
    }

    private KeyStore openKeyStore() throws IOException {
        if (this.getKeyStore() == null) {
            throw new IllegalArgumentException("Property 'keyStore' must not be null!");
        } else {
            try {
                KeyStore keyStore = KeyStore.getInstance(this.getKeyStoreType());
                try (InputStream keyStoreStream = new BufferedInputStream(this.getKeyStore().getInputStream())) {
                    keyStore.load(keyStoreStream, this.getKeyStorePassword());
                }
                return keyStore;
            } catch (Exception e) {
                throw new IOException("Cannot load KeyStore from: " + this.getKeyStore(), e);
            }
        }
    }

    private X509CertificateHolder openCertificate(DataSource certificateResource) throws IOException {
        try {
            CertificateFactory certficateFactory = CertificateFactory.getInstance("X.509");
            try (InputStream certificateStream = new BufferedInputStream(certificateResource.getInputStream())) {
                X509Certificate certificate = (X509Certificate)certficateFactory.generateCertificate(certificateStream);
                return new X509CertificateHolder(certificate.getEncoded());
            }
        } catch (Exception e) {
            throw new IOException("Cannot open certificate file at: " + certificateResource, e);
        }
    }

    public DataSource getKeyStore() {
        return this.keyStore;
    }
    public void setKeyStore(DataSource keyStore) {
        this.keyStore = keyStore;
    }

    public String getKeyStoreType() {
        return this.keyStoreType;
    }
    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public char[] getKeyStorePassword() {
        return this.keyStorePassword;
    }
    public void setKeyStorePassword(char[] keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public String getKeyName() {
        return this.keyName;
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public DataSource getCert() {
        return this.cert;
    }
    public void setCert(DataSource cert) {
        this.cert = cert;
    }

    public DataSource getCa() {
        return this.ca;
    }
    public void setCa(DataSource ca) {
        this.ca = ca;
    }

}
