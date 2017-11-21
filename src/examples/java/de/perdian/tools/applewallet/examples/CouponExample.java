package de.perdian.tools.applewallet.examples;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.activation.URLDataSource;

import de.perdian.tools.applewallet.Barcode;
import de.perdian.tools.applewallet.BarcodeFormat;
import de.perdian.tools.applewallet.Color;
import de.perdian.tools.applewallet.Coupon;
import de.perdian.tools.applewallet.Image;
import de.perdian.tools.applewallet.TextField;

/**
 * Creates a Apple Wallet pass for coupon pass type
 *
 * @author Christian Robert
 */

public class CouponExample {

    public static Coupon createCoupon() {
        Coupon pass = new Coupon();
        pass.setPassTypeIdentifier("YOUR PASS TYPE IDENTIFIER");
        pass.setTeamIdentifier("YOUR TEAM IDENTIFIER");
        pass.setOrganizationName("YOUR ORGANIZATION NAME");
        pass.setSerialNumber("123456");
        pass.setDescription("Coupon for your next purchase");
        pass.setPrimaryField(new TextField("discount", "Valid for all purchases", "50% OFF"));
        pass.setAuxiliaryFields(Arrays.asList(new TextField("validUntil", "Valid until", "October 12th 2018")));
        pass.setBarcodes(Arrays.asList(new Barcode(BarcodeFormat.PDF417, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.")));
        pass.setExpirationDate(Instant.now().plus(100, ChronoUnit.DAYS));
        pass.setRelevantDate(Instant.now());
        pass.setBackgroundColor(new Color(200, 200, 200));
        pass.setForegroundColor(new Color(50, 50, 0));
        pass.setLabelColor(new Color(50, 50, 0));
        pass.setIcon(Image.from(new URLDataSource(BoardingPassExample.class.getResource("logo-beauty-box.png"))));
        pass.setLogo(Image.from(new URLDataSource(BoardingPassExample.class.getResource("logo-beauty-box.png"))));
        return pass;
    }

}
