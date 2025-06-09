package org.wanderers;

import org.wanderers.*;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.time.LocalDate;
import static org.wanderers.BookingService.ID_DELIMITER;

public class BookingService implements GeneralService {

    private ArrayList<BookingDetail> bookingInformation;
    private DataStorage store;
    public static final String ID_DELIMITER = "\\.";

    public BookingService(DataStorage store) {
        this.bookingInformation = store.getBookingInformation();
        this.store = store;
    }

    public String createBookingDetail(String bookID, String userID, String packageName, LocalDate bookingDate) {

        //Validation methods
        bookID = GenerateID();
        //Check for redundancy

        BookingDetail newBookingDetail = new BookingDetail(bookID, userID, packageName, bookingDate);
        store.addBookingDetail(newBookingDetail);
        store.saveToFile();
        return "Booking ID [" + bookID + "] created successfully for " + userID + "!";
    }

    public String GenerateID() {
        /*
     * ID Naming Convention for BookingDetail Class
     *  - for BookID starts with 'B' and then any 
     *    numbers that starts with 1 separated by period(.) as regex 
     *  - Any FK (Foreign Keys) that are associated with 
     *    a userID PK must have the same number
     *  - the first bookID will be: B1
         */
        String ID = "B.1";

        for (int i = 0; i < bookingInformation.size(); i++) {
            if (ID.equals(bookingInformation.get(i).bookID)) {
                try {
                    String[] fragmentedID = ID.split(ID_DELIMITER);
                    /*Debug*/
                    System.out.println("Debug: fragmentID[]: " + fragmentedID[0] + ", " + fragmentedID[1]);
                    int idNumber = Integer.parseInt(fragmentedID[1].trim()) + 1;
                    ID = String.join(".", fragmentedID[0].trim(), Integer.toString(idNumber));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error Generating ID: " + e.getMessage());
                }
            }
        }

        return ID;
    }

}
