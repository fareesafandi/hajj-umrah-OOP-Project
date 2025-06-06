package org.wanderers;

import java.text.ParseException;
import java.util.Date;

public class BookingDetail extends AbstractUser {

    public String bookID;
    private String userID;
    private String packageName;
    private Date bookingDate;

    public BookingDetail(String bookID, String userID, String packageName, Date bookingDate) {
        this.bookID = bookID;
        this.userID = userID;
        this.packageName = packageName;
        this.bookingDate = bookingDate;
    }

    public String getBookID() {
        return bookID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPackageName() {
        return packageName;
    }

    public Date bookingDate() {
        return bookingDate;
    }

    public String toFileFormat() {
        return "Booking Detail : "
                + bookID + "|"
                + userID + "|"
                + packageName + "|"
                + bookingDate.toString() + "|";

        // format for storing in Data.txt
        // Booking Detail : bookID | user ID | packageName | bookingDate
    }


    public static BookingDetail fromFileFormat(String line) throws ParseException {

        String[] data = line.split("\\|");

        String bookID = data[1].trim();
        String userID = data[2].trim();
        String packageName = data[3].trim();
        Date bookingDate = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(data[4].trim());

        return new BookingDetail(bookID, userID, packageName, bookingDate);
        // return Booking instance
    }


}
