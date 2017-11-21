package de.perdian.tools.applewallet.examples;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.activation.URLDataSource;

import de.perdian.tools.applewallet.Barcode;
import de.perdian.tools.applewallet.BarcodeFormat;
import de.perdian.tools.applewallet.Image;
import de.perdian.tools.applewallet.StoreCard;
import de.perdian.tools.applewallet.TextField;

/**
 * Creates a Apple Wallet pass for a store card
 *
 * @author Christian Robert
 */

public class StoreCardExample {

    public static StoreCard createStoreCard() {
        StoreCard pass = new StoreCard();
        pass.setPassTypeIdentifier("YOUR PASS TYPE IDENTIFIER");
        pass.setTeamIdentifier("YOUR TEAM IDENTIFIER");
        pass.setOrganizationName("YOUR ORGANIZATION NAME");
        pass.setSerialNumber("123456");
        pass.setDescription("Generic for your next purchase");
        pass.setPrimaryField(new TextField("customer", "John Doe", "1234567890"));
        pass.setBarcodes(Arrays.asList(new Barcode(BarcodeFormat.QR, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.")));
        pass.setExpirationDate(Instant.now().plus(100, ChronoUnit.DAYS));
        pass.setRelevantDate(Instant.now());
        pass.setIcon(Image.from(new URLDataSource(StoreCardExample.class.getResource("logo-yoga-baby.png"))));
        pass.setLogo(Image.from(new URLDataSource(StoreCardExample.class.getResource("logo-yoga-baby.png"))));
        return pass;
    }

}
