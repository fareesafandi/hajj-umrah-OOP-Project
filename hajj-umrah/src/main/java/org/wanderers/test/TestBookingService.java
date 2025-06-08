package org.wanderers.test;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import org.wanderers.*;

public class TestBookingService {

    public static void main(String[] args) {

        DataStorage store = new DataStorage();
        BookingService booking = new BookingService(store);
        Scanner scanner = new Scanner(System.in);  // Only one scanner needed

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {

            System.out.println("Enter details to book an appointment: ");
            System.out.print("Booking ID: ");
            String bookID = scanner.nextLine();

            System.out.print("User ID: ");
            String userID = scanner.nextLine();

            System.out.print("Package Name: ");
            String packageName = scanner.nextLine();

            // parse date for booking
            System.out.print("Booking Date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();

            Date bookingDate = null;
            try {
                bookingDate = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                continue; // go back to start of loop
            }

            LocalDate localBookingDate = bookingDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            // Create booking
            booking.createBookingDetail(bookID, userID, packageName, localBookingDate);

            System.out.println("Booking created successfully!");

            System.out.println("Press 'q' to quit");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting booking test.");
                break;
            }
        }
    }
}
