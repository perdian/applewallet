package de.perdian.tools.applewallet.examples;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.activation.URLDataSource;

import de.perdian.tools.applewallet.Barcode;
import de.perdian.tools.applewallet.BarcodeFormat;
import de.perdian.tools.applewallet.Color;
import de.perdian.tools.applewallet.EventTicket;
import de.perdian.tools.applewallet.Image;
import de.perdian.tools.applewallet.TextField;

/**
 * Creates a Apple Wallet pass for event ticket pass type
 *
 * @author Christian Robert
 */

public class EventTicketExample {

    public static EventTicket createEventTicket() {
        EventTicket pass = new EventTicket();
        pass.setPassTypeIdentifier("YOUR PASS TYPE IDENTIFIER");
        pass.setTeamIdentifier("YOUR TEAM IDENTIFIER");
        pass.setOrganizationName("YOUR ORGANIZATION NAME");
        pass.setSerialNumber("123456");
        pass.setDescription("Ticket for your next event");
        pass.setPrimaryField(new TextField("discount", "Event", "Arthur Dent Live in Concert"));
        pass.setSecondaryFields(Arrays.asList(new TextField("time", "Showtime", "October 12th 2018 20:00")));
        pass.setAuxiliaryFields(Arrays.asList(new TextField("location", "Location", "Super Mega Stadium")));
        pass.setHeaderFields(Arrays.asList(new TextField("booking", "Booking code", "ABC123456XYZ")));
        pass.setBarcodes(Arrays.asList(new Barcode(BarcodeFormat.AZTEC, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.")));
        pass.setExpirationDate(Instant.now().plus(100, ChronoUnit.DAYS));
        pass.setRelevantDate(Instant.now());
        pass.setBackgroundColor(new Color(210, 210, 255));
        pass.setThumbnail(Image.from(new URLDataSource(EventTicketExample.class.getResource("person.jpg"))));
        pass.setIcon(Image.from(new URLDataSource(EventTicketExample.class.getResource("logo-the-dance-studio.png"))));
        pass.setLogo(Image.from(new URLDataSource(EventTicketExample.class.getResource("logo-the-dance-studio.png"))));
        return pass;
    }

}
