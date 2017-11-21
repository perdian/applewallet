package de.perdian.tools.applewallet.examples;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.activation.URLDataSource;

import de.perdian.tools.applewallet.Barcode;
import de.perdian.tools.applewallet.BarcodeFormat;
import de.perdian.tools.applewallet.GenericPass;
import de.perdian.tools.applewallet.Image;
import de.perdian.tools.applewallet.TextField;

/**
 * Creates a Apple Wallet pass for the generic pass type
 *
 * @author Christian Robert
 */

public class GenericPassExample {

    public static GenericPass createGenericPass() {
        GenericPass pass = new GenericPass();
        pass.setPassTypeIdentifier("YOUR PASS TYPE IDENTIFIER");
        pass.setTeamIdentifier("YOUR TEAM IDENTIFIER");
        pass.setOrganizationName("YOUR ORGANIZATION NAME");
        pass.setSerialNumber("123456");
        pass.setDescription("Generic for your next purchase");
        pass.setPrimaryField(new TextField("discount", "Your destination", "Cologne Cathedral"));
        pass.setSecondaryFields(Arrays.asList(new TextField("validUntil", "Valid until", "October 12th 2018")));
        pass.setBarcodes(Arrays.asList(new Barcode(BarcodeFormat.PDF417, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.")));
        pass.setExpirationDate(Instant.now().plus(100, ChronoUnit.DAYS));
        pass.setRelevantDate(Instant.now());
        pass.setThumbnail(Image.from(new URLDataSource(GenericPassExample.class.getResource("location.jpg"))));
        pass.setIcon(Image.from(new URLDataSource(GenericPassExample.class.getResource("logo-the-web-works.png"))));
        pass.setLogo(Image.from(new URLDataSource(GenericPassExample.class.getResource("logo-the-web-works.png"))));
        pass.setLogoText("The very best");
        return pass;
    }

}
