package de.perdian.tools.applewallet.examples;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.activation.URLDataSource;

import de.perdian.tools.applewallet.Barcode;
import de.perdian.tools.applewallet.BarcodeFormat;
import de.perdian.tools.applewallet.BoardingPass;
import de.perdian.tools.applewallet.DateField;
import de.perdian.tools.applewallet.DateStyle;
import de.perdian.tools.applewallet.Image;
import de.perdian.tools.applewallet.TextField;
import de.perdian.tools.applewallet.TransitType;

/**
 * Creates a Apple Wallet pass for the boarding pass type
 *
 * @author Christian Robert
 */

public class BoardingPassExample {

    public static BoardingPass createBoardingPass() {
        BoardingPass pass = new BoardingPass();
        pass.setPassTypeIdentifier("YOUR PASS TYPE IDENTIFIER");
        pass.setTeamIdentifier("YOUR TEAM IDENTIFIER");
        pass.setOrganizationName("YOUR ORGANIZATION NAME");
        pass.setSerialNumber("123456");
        pass.setDescription("Boardingpass for your next flight");
        pass.setTransitType(TransitType.AIR);
        pass.setHeaderFields(Arrays.asList(new TextField("flight", "Flight", "XX1234"), new DateField("date", "Date", Instant.now(), DateStyle.SHORT, DateStyle.NONE)));
        pass.setPrimaryFields(Arrays.asList(new TextField("departureAirport", "Cologne", "CGN"), new TextField("arrivalAirport", "Orlando", "MCO")));
        pass.setAuxiliaryFields(Arrays.asList(new TextField("boardingTime", "Boarding", "14:55"), new TextField("gate", "Gate", "B43"), new TextField("zone", "Zone", "3"), new TextField("seat", "Seat", "42F"), new TextField("class", "Class", "Economy")));
        pass.setSecondaryFields(Arrays.asList(new TextField("passengerName", "Passenger", "Doe, John"), new TextField("status", "Status", "FQTV"), new TextField("bookingReferences", "Booking Ref.", "ABC123")));
        pass.setBarcodes(Arrays.asList(new Barcode(BarcodeFormat.QR, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.")));
        pass.setExpirationDate(Instant.now().plus(100, ChronoUnit.DAYS));
        pass.setRelevantDate(Instant.now());
        pass.setIcon(Image.from(new URLDataSource(BoardingPassExample.class.getResource("logo-fast-banana.png"))));
        pass.setLogo(Image.from(new URLDataSource(BoardingPassExample.class.getResource("logo-fast-banana.png"))));
        return pass;
    }

}
