package de.perdian.tools.applewallettools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * The overall container for all data that should be displayed on an Apple Wallet pass.
 *
 * @author Christian Robert
 */

public abstract class Pass implements Serializable {

    static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(Pass.class);

    private String passTypeIdentifier = null;
    private String serialNumber = null;
    private String description = null;
    private String organizationName = null;
    private String teamIdentifier = null;
    private String appLaunchURL = null;
    private List<Integer> associatedStoreIdentifiers = null;
    private Instant expirationDate = null;
    private Boolean voided = null;
    private List<Beacon> beacons = null;
    private List<Location> locations = null;
    private Integer maxDistance = null;
    private Instant relevantDate = null;
    private List<Barcode> barcodes = null;
    private Color backgroundColor = null;
    private Color foregroundColor = null;
    private Color labelColor = null;
    private String logoText = null;
    private String webServiceURL = null;
    private String authenticationToken = null;
    private List<Field<?>> primaryFields = null;
    private List<Field<?>> secondaryFields = null;
    private List<Field<?>> auxiliaryFields = null;
    private List<Field<?>> backFields = null;
    private List<Field<?>> headerFields = null;
    private Image logo = null;
    private Image icon = null;

    /**
     * Creates an Apple Wallet pass
     *
     * @param pass
     *      the source object defining the content of the pass
     * @return
     *      the byte representation of the pass. These bytes need to be downloaded by the
     *      user and added into the Apple Wallet app to make the pass visible to the user.
     * @throws SigningException
     *      thrown if the pass cannot be created correctly
     */
    public SignedPass toSignedPass(SigningData signingData) {
        try {

            log.info("Signing Apple Wallet pass for data: {}", this);
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteOutputStream)) {

                // Build the manifest and add into the target ZIP archive
                JsonObject passJsonObject = this.toJsonObject();
                String passJson = new GsonBuilder().setPrettyPrinting().create().toJson(passJsonObject);
                zipOutputStream.putNextEntry(new ZipEntry("pass.json"));
                zipOutputStream.write(passJson.getBytes("UTF-8"));

                // Add all available images as files within the target ZIP archive
                Map<String, Image> images = this.toImages();
                for (Map.Entry<String, Image> imageEntry : images.entrySet()) {
                    if (imageEntry.getValue() != null) {
                        if (imageEntry.getValue().getData() != null) {
                            log.debug("Adding entry to target file: {}", imageEntry.getKey() + ".png");
                            zipOutputStream.putNextEntry(new ZipEntry(imageEntry.getKey() + ".png"));
                            zipOutputStream.write(imageEntry.getValue().getData());
                        }
                        if (imageEntry.getValue().getRetinaData() != null) {
                            log.debug("Adding entry to target file: {}", imageEntry.getKey() + "@2x.png");
                            zipOutputStream.putNextEntry(new ZipEntry(imageEntry.getKey() + "@2x.png"));
                            zipOutputStream.write(imageEntry.getValue().getRetinaData());
                        }
                    }
                }

                // We're done, make sure everything is written into the stream
                zipOutputStream.flush();

            }

            SignedPass signedPass = new SignedPass();
            signedPass.setBytes(byteOutputStream.toByteArray());
            return signedPass;

        } catch (IOException e) {
            throw new PassCreationException("Cannot create new pass", e);
        }
    }

    protected Map<String, Image> toImages() {
        Map<String, Image> images = new HashMap<>();
        images.put("logo", this.getLogo());
        images.put("icon", this.getIcon());
        return images;
    }

    /**
     * Append the details specific to the current style
     */
    protected JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        if (this.getSerialNumber() == null || this.getSerialNumber().isEmpty()) {
            throw new IllegalArgumentException("Property 'serialNumber' must not be empty");
        } else if (this.getPassTypeIdentifier() == null || this.getPassTypeIdentifier().isEmpty()) {
            throw new IllegalArgumentException("Property 'passTypeIdentifier' must not be empty");
        } else if (!this.getPassTypeIdentifier().startsWith("pass.")) {
            throw new IllegalArgumentException("Property 'passTypeIdentifier' must start with 'pass.'");
        } else if (this.getDescription() == null || this.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Property 'description' must not be empty");
        } else if (this.getOrganizationName() == null || this.getOrganizationName().isEmpty()) {
            throw new IllegalArgumentException("Property 'organizationName' must not be empty");
        } else if (this.getTeamIdentifier() == null || this.getTeamIdentifier().isEmpty()) {
            throw new IllegalArgumentException("Property 'teamIdentifier' must not be empty");
        } else {

            jsonObject.addProperty("description", this.getDescription());
            jsonObject.addProperty("formatVersion", 1);
            jsonObject.addProperty("passTypeIdentifier", this.getPassTypeIdentifier());
            jsonObject.addProperty("serialNumber", this.getSerialNumber());
            jsonObject.addProperty("organizationName", this.getOrganizationName());
            jsonObject.addProperty("teamIdentifier", this.getTeamIdentifier());
            jsonObject.addProperty("appLaunchURL", this.getAppLaunchURL());

            if (this.getAssociatedStoreIdentifiers() != null && !this.getAssociatedStoreIdentifiers().isEmpty()) {
                JsonArray associatedStoreIdentifiers = new JsonArray();
                this.getAssociatedStoreIdentifiers().forEach(associatedStoreIdentifiers::add);
                jsonObject.add("associatedStoreIdentifiers", associatedStoreIdentifiers);
            }

            jsonObject.addProperty("voided", this.getVoided());
            jsonObject.addProperty("expirationDate", this.getExpirationDate() == null ? null : this.getExpirationDate().toString());
            jsonObject.add("beacons", PassHelper.toJsonArray(this.getBeacons(), Beacon::toJsonObject));
            jsonObject.add("locations", PassHelper.toJsonArray(this.getLocations(), Location::toJsonObject));
            jsonObject.addProperty("maxDistance", this.getMaxDistance());
            jsonObject.addProperty("relevantDate", this.getRelevantDate() == null ? null : this.getRelevantDate().toString());
            jsonObject.add("barcodes", PassHelper.toJsonArray(this.getBarcodes(), Barcode::toJsonObject));
            jsonObject.addProperty("backgroundColor", this.getBackgroundColor() == null ? null : this.getBackgroundColor().toString());
            jsonObject.addProperty("foregroundColor", this.getForegroundColor() == null ? null : this.getForegroundColor().toString());
            jsonObject.addProperty("labelColor", this.getLabelColor() == null ? null : this.getLabelColor().toString());
            jsonObject.addProperty("logoText", this.getLogoText());

            if (this.getWebServiceURL() == null || this.getWebServiceURL().isEmpty()) {
                if (this.getAuthenticationToken() != null && !this.getAuthenticationToken().isEmpty()) {
                    throw new IllegalArgumentException("Property 'authenticationToken' must be empty if no 'webserviceURL' is set!");
                }
            } else {
                if (this.getAuthenticationToken() == null || this.getAuthenticationToken().isEmpty()) {
                    throw new IllegalArgumentException("Property 'authenticationToken' must not be empty if 'webserviceURL' is set!");
                } else if (this.getAuthenticationToken().length() < 16) {
                    throw new IllegalArgumentException("Property 'authenticationToken' must have at least 16 characters if 'webserviceURL' is set!");
                } else if (!this.getWebServiceURL().startsWith("https://")) {
                    throw new IllegalArgumentException("Property 'webServiceURL' must use the HTTPS protocol!");
                } else {
                    jsonObject.addProperty("authenticationToken", this.getAuthenticationToken());
                    jsonObject.addProperty("webServiceURL", this.getWebServiceURL());
                }
            }

        }
        return jsonObject;
    }

    public String getPassTypeIdentifier() {
        return this.passTypeIdentifier;
    }
    public void setPassTypeIdentifier(String passTypeIdentifier) {
        this.passTypeIdentifier = passTypeIdentifier;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getTeamIdentifier() {
        return this.teamIdentifier;
    }
    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    public String getAppLaunchURL() {
        return this.appLaunchURL;
    }
    public void setAppLaunchURL(String appLaunchURL) {
        this.appLaunchURL = appLaunchURL;
    }

    public List<Integer> getAssociatedStoreIdentifiers() {
        return this.associatedStoreIdentifiers;
    }
    public void setAssociatedStoreIdentifiers(List<Integer> associatedStoreIdentifiers) {
        this.associatedStoreIdentifiers = associatedStoreIdentifiers;
    }

    public Instant getExpirationDate() {
        return this.expirationDate;
    }
    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getVoided() {
        return this.voided;
    }
    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public List<Beacon> getBeacons() {
        return this.beacons;
    }
    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public List<Location> getLocations() {
        return this.locations;
    }
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Integer getMaxDistance() {
        return this.maxDistance;
    }
    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Instant getRelevantDate() {
        return this.relevantDate;
    }
    public void setRelevantDate(Instant relevantDate) {
        this.relevantDate = relevantDate;
    }

    public List<Barcode> getBarcodes() {
        return this.barcodes;
    }
    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return this.foregroundColor;
    }
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getLabelColor() {
        return this.labelColor;
    }
    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }

    public String getLogoText() {
        return this.logoText;
    }
    public void setLogoText(String logoText) {
        this.logoText = logoText;
    }

    public String getWebServiceURL() {
        return this.webServiceURL;
    }
    public void setWebServiceURL(String webServiceURL) {
        this.webServiceURL = webServiceURL;
    }

    public String getAuthenticationToken() {
        return this.authenticationToken;
    }
    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public List<Field<?>> getPrimaryFields() {
        return this.primaryFields;
    }

    public void setPrimaryFields(List<Field<?>> primaryFields) {
        this.primaryFields = primaryFields;
    }

    public List<Field<?>> getSecondaryFields() {
        return this.secondaryFields;
    }
    public void setSecondaryFields(List<Field<?>> secondaryFields) {
        this.secondaryFields = secondaryFields;
    }

    public List<Field<?>> getAuxiliaryFields() {
        return this.auxiliaryFields;
    }
    public void setAuxiliaryFields(List<Field<?>> auxiliaryFields) {
        this.auxiliaryFields = auxiliaryFields;
    }

    public List<Field<?>> getBackFields() {
        return this.backFields;
    }
    public void setBackFields(List<Field<?>> backFields) {
        this.backFields = backFields;
    }

    public List<Field<?>> getHeaderFields() {
        return this.headerFields;
    }
    public void setHeaderFields(List<Field<?>> headerFields) {
        this.headerFields = headerFields;
    }

    public Image getLogo() {
        return this.logo;
    }
    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public Image getIcon() {
        return this.icon;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
    }

}
