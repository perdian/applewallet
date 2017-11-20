package de.perdian.tools.walletutil.examples.dummyair;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.activation.FileDataSource;

import de.perdian.tools.applewallettools.Barcode;
import de.perdian.tools.applewallettools.BarcodeFormat;
import de.perdian.tools.applewallettools.Beacon;
import de.perdian.tools.applewallettools.BoardingPass;
import de.perdian.tools.applewallettools.Image;
import de.perdian.tools.applewallettools.Location;
import de.perdian.tools.applewallettools.TextField;
import de.perdian.tools.applewallettools.TransitType;

/**
 * Creates a Apple Wallet pass for the fictional "dummy air" airline
 *
 * @author Christian Robert
 */

public class DummyAirBoardingPassExample {

    public static void main(String[] args) throws IOException {

        BoardingPass boardingPass = new BoardingPass();
        boardingPass.setPassTypeIdentifier("pass.com.example.boardingpass");
        boardingPass.setSerialNumber("123456");
        boardingPass.setDescription("Boardingpass for your next flight");
        boardingPass.setPrimaryFields(Arrays.asList(new TextField("departureAirport", "From", "CGN"), new TextField("arrivalAirport", "To", "MCO")));
        boardingPass.setSecondaryFields(Arrays.asList(new TextField("flightNumber", "Flight", "XX1234"), new TextField("date", "Date", "01JAN17"), new TextField("boarding", "Boarding", "12:34")));
        boardingPass.setAuxiliaryFields(Arrays.asList(new TextField("passengerName", "Name", "Doe, John"), new TextField("status", "Status", "Frequent Traveler")));
        boardingPass.setBeacons(Arrays.asList(new Beacon("1234567890A"), new Beacon("1234567890B")));
        boardingPass.setLocations(Arrays.asList(new Location(50.865917D, 7.142744D), new Location(28.429394D, -81.308994D)));
        boardingPass.setTransitType(TransitType.AIR);
        boardingPass.setOrganizationName("Dummy Air");
        boardingPass.setTeamIdentifier("Dummy team");
        boardingPass.setExpirationDate(Instant.now().plus(100, ChronoUnit.DAYS));
        boardingPass.setRelevantDate(Instant.now());
        boardingPass.setVoided(Boolean.FALSE);
        boardingPass.setBarcodes(Arrays.asList(new Barcode(BarcodeFormat.QR, "TEST123TEST")));
        boardingPass.setIcon(Image.from(new FileDataSource(new File("logo.png"))));

        byte[] boardingPassBytes = boardingPass.toSignedPass(in -> in);
        try (ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(boardingPassBytes)) ) {
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                int entrySize = 0;
                for (int data = zipInputStream.read(); data > -1; data = zipInputStream.read()) {
                    entrySize++;
                }
                System.out.println(nextEntry + " [" + entrySize + " bytes]");
            }
        }

    }

}
