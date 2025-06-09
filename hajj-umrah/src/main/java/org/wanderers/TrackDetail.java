package org.wanderers;

// This class will hold the specific tracking information for a single participant
// This is done because too much info would make the class TrackingService too cluttered
// May be inefficient but at least it works but what do I know

public class TrackDetail {
    private String currUserID;
    private String tracID;
    private String hajjJourneyStatus; // "Ongoing", "Pending", "Ended"
    private String bookingProgress;   // "Confirmed", "Cancelled", "Pending"
    private String hajjProgress;      // "Visa Approved", "Flight Booked", "Makkah Arrival" (fix)

    //constructor
    public TrackDetail(String currUserID, String tracID, String hajjJourneyStatus, String bookingProgress, String hajjProgress) {
        this.currUserID = currUserID;
        this.tracID = tracID;
        this.hajjJourneyStatus = hajjJourneyStatus;
        this.bookingProgress = bookingProgress;
        this.hajjProgress = hajjProgress;
    }

    // Getters
    public String getCurrUserID() {
        return currUserID;
    }

    public String getTracID() {
        return tracID;
    }

    public String getHajjJourneyStatus() {
        return hajjJourneyStatus;
    }

    public String getBookingProgress() {
        return bookingProgress;
    }

    public String getHajjProgress() {
        return hajjProgress;
    }

    // Setters 
    // there is not setter for user ID and Trac ID bcs those are not sopossed to be altered after creation
    public void setHajjJourneyStatus(String hajjJourneyStatus) {
        this.hajjJourneyStatus = hajjJourneyStatus;
    }

    public void setBookingProgress(String bookingProgress) {
        this.bookingProgress = bookingProgress;
    }

    public void setHajjProgress(String hajjProgress) {
        this.hajjProgress = hajjProgress;
    }

    // Method to convert to file format (for DataStorage)
    public String toFileFormat() {
        return "TRACKDETAIL" + "|" +
            currUserID + "|" +
            tracID + "|" +
            hajjJourneyStatus + "|" +
            bookingProgress + "|" +
            hajjProgress + "|";
    }

    // Static method to create TrackDetail from file format (for DataStorage loading)
    // why do I need to use static??
    // Sebab takde object sebelum ni, static tak perlukan an existing instance to be executed
    // what is regex split
    // split() is a method in Java's String class that divides a string into an array of substrings based on a delimiter
    // In simlpler term "nak tukar string jadi array"

    public static TrackDetail fromFileFormat(String line) {
        String[] data = line.split("\\|"); // Use \\| for regex split 
        if (data.length >= 6 && data[0].trim().equals("TRACKDETAIL")) { // Ensure enough elements and correct type
            return new TrackDetail(
                data[1].trim(),
                data[2].trim(),
                data[3].trim(),
                data[4].trim(),
                data[5].trim()
            );
        }
        return null; 
    }
}